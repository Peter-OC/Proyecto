import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'Pizzeria';
  items: MenuItem[];
  ngOnInit() {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-fw pi-home',
        routerLink: '/home',
      },
      {
        label: 'Productos',
        routerLink: '/productos',
        items: [
          {
            label: 'Pizzas',
            routerLink: '/pizzas',
          },
          {
            label: 'Entrantes',
            routerLink: '/Entrantes',
          },
          {
            label: 'Bebidas',
            routerLink: '/Bebidas',
          },
        ],
      },
      {
        label: 'Login',
        icon: 'pi pi-fw pi-user',
        routerLink: '/login',
      },
      {
        label: 'Registro',
        icon: 'pi pi-fw pi-sign-in',
        routerLink: '/registro',
      },
      {
        label: 'Carrito',
        icon: 'pi pi-fw pi-shopping-cart',
        routerLink: '/carrito',
      },
    ];
  }
}
