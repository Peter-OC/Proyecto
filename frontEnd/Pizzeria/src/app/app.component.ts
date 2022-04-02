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
        label: 'Pizzas',
        items: [
          {
            label: 'Categoria 1',
          },
          {
            label: 'Categoria 2',
          },
          {
            label: 'Categoria 3',
          },
        ],
      },
      {
        label: 'Entrantes',
        routerLink: '/login',
      },
      {
        label: 'Bebidas',
      },
      {
        label: 'Login',
        routerLink: '/login',
      },
    ];
  }
}
