import { Component, OnInit } from '@angular/core';
import { PedidosViewModelService } from '../servicios.service';

 import { MessageService, PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-cocina',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService,
     private messageService: MessageService, private primengConfig: PrimeNGConfig, private myService: PedidosViewModelService,
    ) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }

  ngOnInit(): void {
    this.vm.listCocina();
  }

  onConfirm() {
    this.myService.si();
    this.messageService.clear('c');
}

onReject() {
    this.messageService.clear('c');
}


}

export const COCINA_COMPONENTES = [
  CocinaComponent,
];
