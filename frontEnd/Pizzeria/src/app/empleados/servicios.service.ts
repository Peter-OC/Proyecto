import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpContext,
  HttpContextToken,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NotificationService } from '../common-services';
import { LoggerService } from 'src/lib/my-core';
import { Router } from '@angular/router';
import { AUTH_REQUIRED } from '../security';
import { RESTDAOService } from '../base-code/RESTDAOService';
import { ModoCRUD } from '../base-code/tipos';


@Injectable({
  providedIn: 'root',
})
export class PedidosViewModelService {
  protected listURL = '/cocina';
  protected modo: 'cocina' | 'repartidor' = 'cocina';
  protected listadoOrdered: Array<any> = [];
  protected listadoInProcess: Array<any> = [];
  protected listadoReadies: Array<any> = [];
  protected listadoSents: Array<any> = [];
  protected elemento: any = {};
  protected idOriginal: any = null;

  constructor(
    protected notify: NotificationService,
    protected out: LoggerService,
    protected dao: PedidosEmpleadoDAOService,
    protected router: Router
  ) {}

  public get Modo(): 'cocina' | 'repartidor' {
    return this.modo;
  }
  public get ListadoOrdered(): Array<any> {
    return this.listadoOrdered;
  }
  public get ListadoInProcess(): Array<any> {
    return this.listadoInProcess;
  }
  public get ListadoReadies(): Array<any> {
    return this.listadoReadies;
  }
  public get ListadoSents(): Array<any> {
    return this.listadoSents;
  }

  public get Elemento(): any {
    return this.elemento;
  }

  public listCocina(): void {
    this.dao.getOrdered().subscribe({
      next: (data) => {
        this.listadoOrdered = data;
        this.modo = 'cocina';
      },
      error: (err) => this.notify.add(err.message),
    });
    this.dao.getInProcess().subscribe({
      next: (data) => {
        this.listadoInProcess = data;
        this.modo = 'cocina';
      },
      error: (err) => this.notify.add(err.message),
    });
  }

  public listRepartidor(): void {
    this.dao.getReadies().subscribe({
      next: (data) => {
        this.listadoReadies = data;
        this.modo = 'repartidor';
      },
      error: (err) => this.notify.add(err.message),
    });
    this.dao.getSents().subscribe({
      next: (data) => {
        this.listadoSents = data;
        this.modo = 'repartidor';
      },
      error: (err) => this.notify.add(err.message),
    });
  }

  clear() {
    this.elemento = {};
    this.idOriginal = null;
    this.listadoOrdered = [];
    this.listadoInProcess = [];
    this.listadoReadies = [];
    this.listadoSents = [];
  }

  public cancel(): void {
    this.elemento = {};
    this.idOriginal = null;
    if(this.modo === 'cocina')
      this.listCocina();
    else
      this.listRepartidor();
    // this.list();
    // this.router.navigateByUrl(this.listURL);
  }

  public send(id: number): void {
      this.dao.change(id).subscribe({
        next: (data) => this.cancel(),
        error: (err) => this.notify.add(err.message),
       });
  }

}

@Injectable({ providedIn: 'root' })
export class PedidosEmpleadoDAOService extends RESTDAOService<any, any> {
  constructor(http: HttpClient) {
    super(http, 'order/', {
      context: new HttpContext().set(AUTH_REQUIRED, true),
    });
  }
  public getOrdered() : Observable<Array<any>>{
    return this.http.get<Array<any>>(this.baseUrl + 'ordered', this.option);
  }
  public getInProcess() : Observable<Array<any>>{
    return this.http.get<Array<any>>(this.baseUrl + 'inProcess', this.option);
  }
  public getReadies() : Observable<Array<any>>{
    return this.http.get<Array<any>>(this.baseUrl + 'readies', this.option);
  }
  public getSents() : Observable<Array<any>>{
    return this.http.get<Array<any>>(this.baseUrl + 'sents', this.option);
  }

  public override change(id: any) : Observable<any> {
      return this.http.put(this.baseUrl + 'change/' + id, null, this.option)
  }

}
