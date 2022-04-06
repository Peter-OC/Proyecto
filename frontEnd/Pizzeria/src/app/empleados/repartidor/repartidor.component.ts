import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { PedidosViewModelService } from '../servicios.service';

@Component({
  selector: 'app-repartidor',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./repartidor.component.scss'],
})
export class RepartidorComponent implements OnInit {
     constructor(protected vm: PedidosViewModelService,private messageService: MessageService,private primengConfig: PrimeNGConfig, private myService: PedidosViewModelService,) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }

  ngOnInit(): void {
    this.vm.listRepartidor();
  }

  onConfirm() {
    this.myService.si();
    this.messageService.clear('c');
}

onReject() {
    this.messageService.clear('c');
}
}


export const REPARTIDOR_COMPONENTES = [
  RepartidorComponent,
];
