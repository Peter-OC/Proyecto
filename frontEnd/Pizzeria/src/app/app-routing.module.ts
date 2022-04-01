import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IngredientesAddComponent, IngredientesEditComponent, IngredientesListComponent, IngredientesViewComponent } from './ingredientes/componente.component';
import { HomeComponent } from './main';
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

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
