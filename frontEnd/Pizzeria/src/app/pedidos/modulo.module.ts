import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../common-services';
import { PEDIDOS_COMPONENTES } from './componente.component';
import { ButtonModule } from 'primeng/button';
import { Table, TableModule } from 'primeng/table';
import { RatingModule } from 'primeng/rating';

@NgModule({
  declarations: [PEDIDOS_COMPONENTES],
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
  ],
  exports: [PEDIDOS_COMPONENTES],
})
export class PedidosModule {}
