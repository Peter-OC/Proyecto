import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {  CocinaComponent, COCINA_COMPONENTES } from './cocina/cocina.component';
import { RepartidorComponent, REPARTIDOR_COMPONENTES } from './repartidor/repartidor.component';
import { RouterModule, Routes } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { RatingModule } from 'primeng/rating';
import {ToolbarModule} from 'primeng/toolbar';
import {InputNumberModule} from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms';

import {ToastModule} from 'primeng/toast';
import { PedidosViewModelService } from './servicios.service';

const routes: Routes = [
  {
    path: 'repartidor', children: [
      { path: '', component: RepartidorComponent },
    ]
  },
  {
    path: 'cocina', children: [
      { path: '', component: CocinaComponent },
      ]
  },
]

@NgModule({
  declarations: [COCINA_COMPONENTES, REPARTIDOR_COMPONENTES, ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ButtonModule,
    TableModule,
    RatingModule,
    ToolbarModule,
    InputNumberModule,
    FormsModule,
    ToastModule,

  ],

  exports: [COCINA_COMPONENTES, REPARTIDOR_COMPONENTES,],
  providers: [PedidosViewModelService]
})
export class EmpleadosModule { }
