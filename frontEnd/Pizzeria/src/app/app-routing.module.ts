import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IngredientesAddComponent, IngredientesEditComponent, IngredientesListComponent, IngredientesViewComponent } from './manager/ingredientes/componente.component';
import { HomeComponent } from './main';
import { PedidosAddComponent, PedidosEditComponent, PedidosListComponent, PedidosViewComponent } from './pedidos/componente.component';
import { PizzacardComponent } from './pizzacard/pizzacard.component';

const routes: Routes = [
  {
    path: '', component: PizzacardComponent
  },
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

  {
    path: 'manager', loadChildren: () => import('./manager/manager.module').then(mod => mod.ManagerModule)
  },
  {
    path: 'empleado', loadChildren: () => import('./empleados/empleados.module').then(mod => mod.EmpleadosModule)
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
