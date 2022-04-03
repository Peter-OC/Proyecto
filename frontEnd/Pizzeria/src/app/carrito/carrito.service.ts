import { Injectable } from '@angular/core';
import { ProductosComponent } from '../manager/productos/componente.component';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  items: ProductosComponent[] = [];
/* . . . */

  addToCarrito(product: ProductosComponent) {
    this.items.push(product);
  }

  getItems() {
    return this.items;
  }

  clearCarrito() {
    this.items = [];
    return this.items;
  }
/* . . . */
}


