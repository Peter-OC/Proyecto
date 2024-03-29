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
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { Router } from '@angular/router';



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
    protected router: Router,
    protected dao: DatosDAOService,
    private messageService: MessageService, private primengConfig: PrimeNGConfig

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
  public cambia(): void {
    this.modo = 'view';
  }

  public add(): void {
    this.modo = 'add';
  }
  public volver(): void {
    this.modo = 'edit';
  }
//   showViaService() {
//     this.messageService.add({severity:'success', summary:'Service Message', detail:'Via MessageService'});
// }

  public send(): void {
    this.messageService.add({severity:'success', summary:'Perfil editado con éxito'});
    switch (this.modo) {
      case 'view':

        this.dao.change(this.elemento).subscribe({
          next: (data) => this.cancel(),
          error: (err) => this.notify.add(err.message),
        });
        this.router.navigateByUrl('/');
        break;
      // case 'view':
      //   this.cancel();
      //   break;
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
