import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IngredientesAddComponent, IngredientesEditComponent, IngredientesListComponent, IngredientesViewComponent } from './ingredientes/componente.component';
import { HomeComponent } from './main';
import { PedidosAddComponent, PedidosEditComponent, PedidosListComponent, PedidosViewComponent } from './pedidos/componente.component';
import { PizzacardComponent } from './pizzacard/pizzacard.component';
import { RegistroComponent } from './registro/registro.component';
import { LoginComponent, RegisterUserComponent } from './security';

const routes: Routes = [
  { path: 'registro', component: RegisterUserComponent },
  { path: 'sesion', component: LoginComponent },

  { path: 'ingredientes', children: [
    { path: '', component: IngredientesListComponent},
    { path: 'add', component: IngredientesAddComponent},
    { path: ':id/edit', component: IngredientesEditComponent},
   { path: ':id', component: IngredientesViewComponent},
  ]},

  { path: 'pedidos', children: [
    { path: '', component: PedidosListComponent},
    { path: 'add', component: PedidosAddComponent},
    { path: ':id/edit', component: PedidosEditComponent},
    { path: ':id', component: PedidosViewComponent},
  ]},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
