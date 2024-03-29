import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../../common-services';
import { INGREDIENTES_COMPONENTES } from './componente.component';
import { ButtonModule } from 'primeng/button';
import { Table, TableModule } from 'primeng/table';
import { RatingModule } from 'primeng/rating';
import {ToolbarModule} from 'primeng/toolbar';
import {InputNumberModule} from 'primeng/inputnumber';
import {ToastModule} from 'primeng/toast';

import {PaginatorModule} from 'primeng/paginator';

@NgModule({
  declarations: [INGREDIENTES_COMPONENTES],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild([]),
    MyCoreModule,
    CommonServicesModule,
    ButtonModule,
    TableModule,
    RatingModule,
    FormsModule,
    ToolbarModule,
    InputNumberModule,
    PaginatorModule,
    ToastModule
  ],
  exports: [INGREDIENTES_COMPONENTES],
})
export class IngredientesModule {}
