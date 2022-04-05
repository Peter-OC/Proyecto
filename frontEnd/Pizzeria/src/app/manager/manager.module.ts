import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesModule } from './ingredientes';
import { RouterModule, Routes } from '@angular/router';
import { IngredientesAddComponent, IngredientesComponent, IngredientesEditComponent, IngredientesViewComponent } from './ingredientes/componente.component';
import { UsuariosModule } from './usuarios';
import { ProductosModule } from './productos';
import { ProductosAddComponent, ProductosComponent, ProductosEditComponent, ProductosListComponent, ProductosViewComponent } from './productos/componente.component';
import { UsuariosComponent, UsuariosEditComponent, UsuariosListComponent, UsuariosViewComponent } from './usuarios/componente.component';

const routes: Routes = [
  {
    path: 'productos', children: [
      { path: '', component: ProductosListComponent},
      { path: 'add', component: ProductosAddComponent },
      { path: ':id/edit', component: ProductosEditComponent },
      { path: ':id', component: ProductosViewComponent },
    ]
  },
  {
    path: 'usuarios', children: [
      { path: '', component: UsuariosListComponent },
      { path: ':id/edit', component: UsuariosEditComponent },
      { path: ':id', component: UsuariosViewComponent },
    ]
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
