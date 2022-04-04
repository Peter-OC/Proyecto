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
        label: 'Productos',
        routerLink: '/productos',
      },
      {
        label: 'Entrantes',
        routerLink: '/entrantes',
        visible: this.loginSrv.isInRoles('ROLE_ADMIN', 'ROLE_EMPLOYED')
      },
      {
        label: 'Bebidas',
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
