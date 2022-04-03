import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonServicesModule } from 'src/app/common-services';
import { MyCoreModule } from 'src/lib/my-core';
import { CATALOGO_COMPONENTES } from './componente.component';
import {CardModule} from 'primeng/card';



@NgModule({
  declarations: [
    CATALOGO_COMPONENTES
  ],
  imports: [
    CommonModule, FormsModule, RouterModule.forChild([]),
    MyCoreModule, CommonServicesModule, CardModule,

  ],
  exports: [
    CATALOGO_COMPONENTES,
  ]
})
export class CatalogoModule { }
