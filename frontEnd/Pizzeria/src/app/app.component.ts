import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { AuthService } from './security';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'Pizzeria';
  items: MenuItem[];

  constructor(private loginSrv: AuthService) {

  }
  ngOnInit() {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-fw pi-home',
        routerLink: '/',
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
        visible: !this.loginSrv.isAutenticated
      },
      {
        label: 'Registro',
        icon: 'pi pi-fw pi-sign-in',
        routerLink: '/registro',
        visible: !this.loginSrv.isAutenticated
      },
      {
        label: 'Perfil',
        icon: 'pi pi-fw pi-user',
        routerLink: '/perfil',
        visible: this.loginSrv.isAutenticated
      },
      {
        label: 'Carrito',
        icon: 'pi pi-fw pi-shopping-cart',
        routerLink: '/carrito',
        visible: this.loginSrv.isAutenticated
      },
    ];
  }
}
