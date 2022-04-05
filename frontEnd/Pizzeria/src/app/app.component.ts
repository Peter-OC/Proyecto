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

  constructor(private loginSrv: AuthService) {}
  ngOnInit() {
    this.loginSrv.Notificacion.subscribe({
      next: () => this.init()
        ,
    });
    this.init();
  }

  init() {
    (this.items = [
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
            visible: !this.loginSrv.isAutenticated,
          },
          {
            label: 'Registro',
            icon: 'pi pi-fw pi-sign-in',
            routerLink: '/registro',
            visible: !this.loginSrv.isAutenticated,
          },
          {
            label: 'Carrito',
            icon: 'pi pi-fw pi-shopping-cart',
            routerLink: '/carrito',
            visible: this.loginSrv.isAutenticated,
          },
          {
            label: 'Cocina',
            icon: 'pi pi-fw pi-info-circle',
            routerLink: '/empleado/cocina',
            visible: this.loginSrv.isInRoles('ROLE_EMPLOYED'),
          },
          {
            label: 'Repartidor',
            icon: 'pi pi-fw pi-info-circle',
            routerLink: '/empleado/repartidor',
            visible: this.loginSrv.isInRoles('ROLE_EMPLOYED'),
          },
          {
            label: 'Perfil',
            icon: 'pi pi-fw pi-user-edit',
            routerLink: '/perfil',
            visible: this.loginSrv.isAutenticated,
          },
          {
            label: 'Cerrar sesión',
            icon: 'pi pi-fw pi-sign-out',
            routerLink: '/login',
            visible: this.loginSrv.isAutenticated,

          },
        ])
  }
}
