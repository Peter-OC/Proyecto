import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../../common-services';
import { USUARIOS_COMPONENTES } from './componente.component';
import { ButtonModule } from 'primeng/button';
import { Table, TableModule } from 'primeng/table';
import { RatingModule } from 'primeng/rating';
import {ToolbarModule} from 'primeng/toolbar';
import {InputNumberModule} from 'primeng/inputnumber';

@NgModule({
  declarations: [USUARIOS_COMPONENTES],
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
    InputNumberModule
  ],
  exports: [USUARIOS_COMPONENTES],
})
export class UsuariosModule {}
