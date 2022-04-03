import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CocinaComponent, CocinaEditComponent, CocinaListComponent, COCINA_COMPONENTES } from './cocina/cocina.component';
import { RepartidorComponent, RepartidorEditComponent, RepartidorListComponent, REPARTIDOR_COMPONENTES } from './repartidor/repartidor.component';
import { RouterModule, Routes } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { RatingModule } from 'primeng/rating';
import {ToolbarModule} from 'primeng/toolbar';
import {InputNumberModule} from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms';


const routes: Routes = [
  {
    path: 'repartidor', children: [
      { path: '', component: RepartidorListComponent },
      { path: ':id/edit', component: RepartidorEditComponent },
    ]
  },
  {
    path: 'cocina', children: [
      { path: '', component: CocinaListComponent },
      { path: ':id/edit', component: CocinaEditComponent },    ]
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
    FormsModule

  ],

  exports: [COCINA_COMPONENTES, REPARTIDOR_COMPONENTES,]
})
export class EmpleadosModule { }
