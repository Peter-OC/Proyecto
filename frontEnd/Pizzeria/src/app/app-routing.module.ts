import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent, RegisterUserComponent } from './security';
import { CatalogoListComponent } from './Usuarios/catalogo/componente.component';

const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  { path: 'productos', component: CatalogoListComponent},
  // { path: 'productos', loadChildren: () => import('./Usuarios/catalogo/componente.component').then(mod => mod.CatalogoListComponent)},

  { path: 'registro', component: RegisterUserComponent },
  { path: 'login', component: LoginComponent },
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
