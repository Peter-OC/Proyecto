import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IngredientesAddComponent, IngredientesEditComponent, IngredientesListComponent, IngredientesViewComponent } from './manager/ingredientes/componente.component';
import { HomeComponent } from './main';
import { PizzacardComponent } from './pizzacard/pizzacard.component';
import { LoginComponent, RegisterUserComponent } from './security';

const routes: Routes = [
  { path: 'registro', component: RegisterUserComponent },
  { path: 'login', component: LoginComponent },
  {
    path: '', component: PizzacardComponent
  },
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
