import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PizzacardComponent } from './pizzacard.component';

import { CardModule, } from 'primeng/card';

@NgModule({
  declarations: [
    PizzacardComponent
  ],
  imports: [
    CommonModule,
    CardModule
  ],

  exports: [
    CardModule,
    PizzacardComponent
  ]
})
export class PizzacardModule { }
