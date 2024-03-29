import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';
import { environment } from 'src/environments/environment';
import { ProductosComponent } from '../manager/productos/componente.component';
import { AUTH_REQUIRED } from '../security';

@Injectable({
  providedIn: 'root',
})
export class CarritoService {
  items: Array<any> = [];

  constructor(private http: HttpClient, private messageService: MessageService,) {}

  addToCarrito(product: any, cantidad = 1) {
     if(localStorage.getItem('AuthService')==null){
      console.log("holaaa");
      this.messageService.add({severity:'error', summary:'Error', detail:'Tienes que estar registrado para añadir productos al carrito'});
      return;
     }

    const ele = this.items.find((item) => item.product.id === product.id);
    if (ele) {
      ele.cantidad++;
    } else {
      this.items.push({ product, cantidad: 1 });
    }
    this.messageService.add({severity:'success', summary:'Producto añadido al carrito'});
  }
  removeFromCarrito(index: number) {
    this.items.splice(index, 1)
  }
  get Items() :  Array<any> {
    return this.items;
  }

  get Total() : number {
    return this.items.reduce((acumulado, item) => acumulado + (item.product.precio * item.cantidad), 0).toFixed(2);
  }

  clearCarrito() {
    this.items = [];
    return this.items;
  }

  public direccion: string = '';

  send() {
    let dto = {
      "direccion": this.direccion,
      "precioPedido": this.Total,
      "productosDelPedido": this.items.map(item => (
        {
          "cantidadProducto": item.cantidad,
          "idProducto": item.product.id,
          "precio": item.cantidad * item.product.precio
        }
      )
      )
    }
    this.http.post(environment.apiURL + 'order', dto,
      { context: new HttpContext().set(AUTH_REQUIRED, true) }).subscribe({
        next: data => this.clearCarrito()
      })
  }
}
