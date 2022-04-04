import { Injectable } from '@angular/core';
import { HttpClient, HttpContext, HttpContextToken } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NotificationService } from '../../common-services/notification.service';
import { LoggerService } from 'src/lib/my-core/services/logger.service';
import { DataSource } from '@angular/cdk/collections';
export type ModoCRUD = 'list' | 'add' | 'edit' | 'view' | 'delete';
export const AUTH_REQUIRED = new HttpContextToken<boolean>(() => false);
@Injectable({
  providedIn: 'root',
})
export class UsuariosViewModelService {
  protected modo: ModoCRUD = 'list';
  protected listado: Array<any> = [];
  protected elemento: any = {};
  protected idOriginal: any = null;

  constructor(
    protected notify: NotificationService,
    protected out: LoggerService,
    protected dao: UsuariosDAOService
  ) {}
  public get Modo(): ModoCRUD {
    return this.modo;
  }
  public get Listado(): Array<any> {
    return this.listado;
  }
  public get Elemento(): any {
    return this.elemento;
  }

  public list(): void {
    this.dao.query().subscribe({
      next: (data) => {
        this.listado = data;
        this.modo = 'list';
      },
      error: (err) => this.notify.add(err.message),
    });
  }
  public add(): void {
    this.elemento = {};
    this.modo = 'add';
  }
  public edit(key: any): void {
    this.dao.get(key).subscribe({
      next: (data) => {
        this.elemento = data;
        this.idOriginal = key;
        this.modo = 'edit';
      },
      error: (err) => this.notify.add(err.message),
    });
  }
  public view(key: any): void {
    this.dao.get(key).subscribe({
      next: (data) => {
        this.elemento = data;
        this.modo = 'view';
      },
      error: (err) => this.notify.add(err.message),
    });
  }
  public delete(key: any): void {
    if (!window.confirm('¿Seguro?')) {
      return;
    }
    this.dao.remove(key).subscribe({
      next: (data) => this.list(),
      error: (err) => this.notify.add(err.message),
    });
  }
  clear() {
    this.elemento = {};
    this.idOriginal = null;
    this.listado = [];
  }
  public cancel(): void {
    this.elemento = {};
    this.idOriginal = null;
    this.list();
  }
  public send(): void {
    switch (this.modo) {
      case 'add':
        this.dao
          .add(this.elemento)
          .subscribe({
            next: (data) => this.cancel(),
            error: (err) => this.notify.add(err.message),
          });
        break;
      case 'edit':
        this.dao
          .change(this.idOriginal, this.elemento)
          .subscribe({
            next: (data) => this.cancel(),
            error: (err) => this.notify.add(err.message),
          });
        break;
      case 'view':
        this.cancel();
        break;
    }
  }

  addRol(prop: any, item: any) {
    if(!this.elemento[prop]) this.elemento[prop] = []
    this.elemento[prop].push(item)
  }
  delRol(prop: any, index: any) {
    this.elemento[prop].splice(index, 1)
  }
}
export abstract class RESTDAOService<T, K> {
  protected baseUrl = environment.securityApiURL;
  constructor(
    protected http: HttpClient,
    entidad: string,
    protected option = {}
  ) {
    this.baseUrl += entidad;
  }
  query(): Observable<Array<T>> {
    return this.http.get<Array<T>>(this.baseUrl, this.option);
  }
  get(id: K): Observable<T> {
    return this.http.get<T>(this.baseUrl + '/' + id, this.option);
  }
  add(item: T): Observable<T> {
    return this.http.post<T>(this.baseUrl, item, this.option);
  }
  change(id: K, item: T): Observable<T> {
    return this.http.put<T>(this.baseUrl + '/' + id, item, this.option);
  }
  remove(id: K): Observable<T> {

    return this.http.delete<T>(this.baseUrl + '/' + id, this.option);
  }
}
@Injectable({ providedIn: 'root' })
export class UsuariosDAOService extends RESTDAOService<any, any> {
  constructor(http: HttpClient) {
    super(http, 'usuarios' , {
      context: new HttpContext().set(AUTH_REQUIRED, true),
    });
  }
}


