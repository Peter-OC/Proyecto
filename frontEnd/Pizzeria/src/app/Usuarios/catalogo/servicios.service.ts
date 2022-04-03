import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpContext,
  HttpContextToken,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NotificationService } from 'src/app/common-services';
import { LoggerService } from 'src/lib/my-core';
import { Router } from '@angular/router';

export const AUTH_REQUIRED = new HttpContextToken<boolean>(() => false);

export type ModoCRUD = 'list' | 'add' | 'edit' | 'view' | 'delete';

export abstract class RESTDAOService<T, K> {
  protected baseUrl = environment.apiURL;
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

@Injectable({
  providedIn: 'root',
})
export class CatalogoDAOService extends RESTDAOService<any, any> {
  constructor(http: HttpClient) {
    super(http, 'productos', {
      context: new HttpContext().set(AUTH_REQUIRED, true),
    });
  }
}

@Injectable({
  providedIn: 'root',
})
export class CatalogoViewModelService {
  protected modo: ModoCRUD = 'list';
  protected listado: Array<any> = [];
  protected elemento: any = {};
  protected idOriginal: any = null;
  protected listURL = '/productos';

  constructor(protected notify: NotificationService, protected out: LoggerService,
    protected dao: CatalogoDAOService, protected router: Router) { }


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
    // this.list();
    this.router.navigateByUrl(this.listURL);
    }
  public send(): void {
    switch (this.modo) {
      case 'add':
        this.dao.add(this.elemento).subscribe({
          next: (data) => this.cancel(),
          error: (err) => this.notify.add(err.message),
        });
        break;
      case 'edit':
        this.dao.change(this.idOriginal, this.elemento).subscribe({
          next: (data) => this.cancel(),
          error: (err) => this.notify.add(err.message),
        });
        break;
      case 'view':
        this.cancel();
        break;
    }
  }
}
