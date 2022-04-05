import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommonServicesModule } from '../common-services';
import { MyCoreModule } from 'src/lib/my-core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { USUARIOS_COMPONENTES } from './componente.component';
import { ButtonModule } from 'primeng/button';
import { InputNumberModule } from 'primeng/inputnumber';
import { RatingModule } from 'primeng/rating';
import { TableModule } from 'primeng/table';
import { ToolbarModule } from 'primeng/toolbar';

import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {InputTextModule} from 'primeng/inputtext';
import {RippleModule} from 'primeng/ripple';


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
    InputNumberModule,
    MessageModule,
    MessagesModule,
    RippleModule,
    InputTextModule,
  ],
  exports: [USUARIOS_COMPONENTES],
})
export class DatosModule {}
