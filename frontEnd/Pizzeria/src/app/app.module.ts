import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LoggerService } from 'src/lib/my-core';
import {FormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IngredientesModule } from './ingredientes';


import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {RatingModule} from 'primeng/rating';
import { PaginatorModule } from 'primeng/paginator';
import { PedidosModule } from './pedidos';


@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    PaginatorModule,
    AppRoutingModule,
    IngredientesModule,
    HttpClientModule,
    ButtonModule,
    RatingModule,
    FormsModule,
    TableModule,
    PedidosModule,
  ],
  providers: [LoggerService],
  bootstrap: [AppComponent],  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule { }
