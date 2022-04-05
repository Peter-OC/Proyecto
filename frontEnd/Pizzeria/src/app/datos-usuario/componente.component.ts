import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { DatosUsuariosViewModelService } from './servicios.service';
import {Message,MessageService} from 'primeng/api';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-datos-usuarios-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],

})
export class DatosUsuariosEditComponent implements OnInit {
  msgs1: Message[];
  msgs2: Message[];
  private obs$: any;
  constructor(
    protected vm: DatosUsuariosViewModelService,
    protected route: ActivatedRoute,
    protected router: Router,
    private messageService: MessageService, private primengConfig: PrimeNGConfig

  ) {}
  public get VM(): DatosUsuariosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.VM.edit();
    this.msgs1 = [
      {severity:'success', summary:'Success', detail:'Message Content'},
      {severity:'info', summary:'Info', detail:'Message Content'},
      {severity:'warn', summary:'Warning', detail:'Message Content'},
      {severity:'error', summary:'Error', detail:'Message Content'}
  ];

  this.primengConfig.ripple = true;
  }

  addMessages() {
    this.msgs2 = [
        {severity:'success', summary:'Success', detail:'Message Content'},
        {severity:'info', summary:'Info', detail:'Message Content'},
        {severity:'warn', summary:'Warning', detail:'Message Content'},
        {severity:'error', summary:'Error', detail:'Message Content'}
    ];
}

clearMessages() {
    this.msgs2 = [];
}

showViaService() {
    this.messageService.add({severity:'success', summary:'Service Message', detail:'Via MessageService'});
}
}

export const USUARIOS_COMPONENTES = [
  DatosUsuariosEditComponent,
];
