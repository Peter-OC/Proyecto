import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesModule } from './ingredientes';
import { RouterModule, Routes } from '@angular/router';
import { IngredientesComponent } from './ingredientes/componente.component';
import { UsuariosModule } from './usuarios';
import { ProductosModule } from './productos';
import { ProductosComponent } from './productos/componente.component';
import { UsuariosComponent } from './usuarios/componente.component';

const routes: Routes = [
  {
    path: 'productos', component: ProductosComponent
  },
  {
    path: 'ususarios', component: UsuariosComponent
  },
  {
    path: 'ingredientes', component: IngredientesComponent
  },

]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    IngredientesModule,
    UsuariosModule,
    ProductosModule


  ]
})
export class ManagerModule { }
