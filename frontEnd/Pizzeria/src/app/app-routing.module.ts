import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent, RegisterUserComponent } from './security';
import { CatalogoListComponent } from './Usuarios/catalogo/componente.component';
import { DatosUsuariosEditComponent } from './datos-usuario/componente.component';
import { CarritoComponent } from './carrito/carrito.component';

const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  { path: 'pizzas', component: CatalogoListComponent},
  // { path: 'productos', loadChildren: () => import('./Usuarios/catalogo/componente.component').then(mod => mod.CatalogoListComponent)},

  { path: 'registro', component: RegisterUserComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'manager', loadChildren: () => import('./manager/manager.module').then(mod => mod.ManagerModule)
  },
  {
    path: 'empleado', loadChildren: () => import('./empleados/empleados.module').then(mod => mod.EmpleadosModule)
  },
  { path: 'perfil', component: DatosUsuariosEditComponent },
  { path: 'carrito', component: CarritoComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
