import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CocinaComponent, CocinaListComponent, COCINA_COMPONENTES } from './cocina/cocina.component';
import { RepartidorComponent } from './repartidor/repartidor.component';
import { RouterModule, Routes } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { RatingModule } from 'primeng/rating';
import {ToolbarModule} from 'primeng/toolbar';
import {InputNumberModule} from 'primeng/inputnumber';


const routes: Routes = [
  {
    path: 'repartidor', component: RepartidorComponent
  },
  {
    path: 'cocina', children: [
      { path: '', component: CocinaListComponent }
    ]
  },
]

@NgModule({
  declarations: [COCINA_COMPONENTES, RepartidorComponent, ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ButtonModule,
    TableModule,
    RatingModule,
    ToolbarModule,
    InputNumberModule,

  ],

  exports: [COCINA_COMPONENTES, ]
})
export class EmpleadosModule { }
