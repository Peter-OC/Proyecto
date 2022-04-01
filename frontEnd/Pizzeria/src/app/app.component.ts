import { Component} from '@angular/core';
import {MenuItem} from 'primeng/api';

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
            label:'Pizzas',
            icon:'pi pi-fw pi-file',
            items:[
                {
                    label:'Categoria 1',
                    icon:'pi pi-fw pi-file',
                },
                {
                    label:'Categoria 2',
                    icon:'pi pi-fw pi-file'
                },
                {
                    label:'Categoria 3',
                    icon:'pi pi-fw pi-file'
                }
            ]
        },
        {
            label:'Entrantes',
            icon:'pi pi-fw pi-pencil'
        },
        {
            label:'Bebidas',
            icon:'pi pi-fw pi-user'
        },
    ];
}
}
