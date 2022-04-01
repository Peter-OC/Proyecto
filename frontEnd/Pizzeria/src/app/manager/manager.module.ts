import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesModule } from './ingredientes';
import { RouterModule, Routes } from '@angular/router';
import { IngredientesAddComponent, IngredientesComponent, IngredientesEditComponent, IngredientesViewComponent } from './ingredientes/componente.component';
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
    path: 'ingredientes', children: [
      { path: '', component: IngredientesComponent },
      { path: 'add', component: IngredientesAddComponent },
      { path: ':id/edit', component: IngredientesEditComponent },
      { path: ':id', component: IngredientesViewComponent },
    ]
  }
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
