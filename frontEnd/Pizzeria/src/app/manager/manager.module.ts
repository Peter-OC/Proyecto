import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IngredientesModule } from '../ingredientes';
import { ProductosModule } from '../productos/productos.module';
import { UsuariosModule } from '../usuarios/usuarios.module';
import { RouterModule, Routes } from '@angular/router';
import { ProductosComponent } from '../productos/productos/productos.component';
import { UsuariosComponent } from '../usuarios/usuarios/usuarios.component';
import { IngredientesComponent } from '../ingredientes/componente.component';

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
    ProductosModule,
    UsuariosModule

  ]
})
export class ManagerModule { }
