import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CocinaComponent } from './cocina/cocina.component';
import { RepartidorComponent } from './repartidor/repartidor.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'repartidor', component: RepartidorComponent
  },
  {
    path: 'cocina', component: CocinaComponent
  },

]


@NgModule({
  declarations: [CocinaComponent, RepartidorComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),

  ]
})
export class EmpleadosModule { }
