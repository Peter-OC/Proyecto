import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpContext,
  HttpContextToken,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoggerService } from 'src/lib/my-core/services/logger.service';
import { NotificationService } from '../common-services';
import { ModoCRUD } from '../base-code/tipos';
import { AUTH_REQUIRED } from '../security';

@Injectable({
  providedIn: 'root',
})
export class DatosUsuariosViewModelService {
  protected modo: ModoCRUD = 'list';
  protected listado: Array<any> = [];
  protected elemento: any = {};
  protected idOriginal: any = null;
  private id: any = localStorage.getItem('datos');

  constructor(
    protected notify: NotificationService,
    protected out: LoggerService,
    protected dao: DatosDAOService
  ) {}
  public get Modo(): ModoCRUD {
    return this.modo;
  }
  public get Listado(): Array<any> {
    console.log(this.id);

    return this.listado;
  }
  public get Elemento(): any {
    return this.elemento;
  }
  public edit(): void {
    this.dao.get().subscribe({
      next: (data) => {
        this.elemento = data;
        this.modo = 'edit';
      },
      error: (err) => this.notify.add(err.message),
    });
  }

  public changePass(): void{
    this.elemento = {oldPassword: '', newPassword: ''};
    this.modo = 'add';
  }

  clear() {
    this.elemento = {};
    this.idOriginal = null;
    this.listado = [];
  }
  public cancel(): void {
    this.elemento = {};
    this.idOriginal = null;
  }
  public send(): void {
    switch (this.modo) {
      case 'edit':
        this.dao.change(this.elemento).subscribe({
          next: (data) => this.cancel(),
          error: (err) => this.notify.add(err.message),
        });
        break;
      case 'view':
        this.cancel();
        break;
      case 'add': //este va a ser el change password
        this.dao.changePass(this.elemento).subscribe({
          next: (data) => this.cancel(),
          error: (err) => this.notify.add(err.message),
        });
        break;
    }
  }
}

@Injectable({ providedIn: 'root' })
export class DatosDAOService {
  private id = localStorage.getItem('name');

  constructor(private http: HttpClient) {}

  get(): Observable<any> {
    return this.http.get<any>(environment.securityURL + 'profile', {
      context: new HttpContext().set(AUTH_REQUIRED, true),
    });
  }
  change(item: any): Observable<any> {
    return this.http.put<any>(environment.securityURL + 'profile', item, {
      context: new HttpContext().set(AUTH_REQUIRED, true),
    });
  }

  changePass(item: any): Observable<any> {
    return this.http.put<any>(environment.securityURL + 'password', item, {
      context: new HttpContext().set(AUTH_REQUIRED, true),
    });
  }
}
