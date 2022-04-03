import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LoggerService, MyCoreModule } from 'src/lib/my-core';
import {FormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {RatingModule} from 'primeng/rating';
import { PaginatorModule } from 'primeng/paginator';
import { CommonComponentModule } from './common-component';

import { MenubarModule } from 'primeng/menubar';
import { InputTextModule } from 'primeng/inputtext';
import { PizzacardModule } from './pizzacard/pizzacard.module';


import {DataViewModule} from 'primeng/dataview';
// import { ProductDetailsComponent } from './product-details/product-details.component';
import { CarritoComponent } from './carrito/carrito.component';
import { CatalogoModule } from './Usuarios/catalogo';


@NgModule({
  declarations: [
    AppComponent,
    // ProductsComponent,
    // ProductDetailsComponent,
    // CarritoComponent,
    // // PizzasComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    PaginatorModule,
    AppRoutingModule,
    HttpClientModule,
    ButtonModule,
    RatingModule,
    FormsModule,
    TableModule,
    MyCoreModule,
    CommonComponentModule,
    MenubarModule,
    InputTextModule,
    CatalogoModule,
  ],
  providers: [LoggerService],
  bootstrap: [AppComponent],  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule { }
