import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { PedidosViewModelService } from '../servicios.service';

@Component({
  selector: 'app-repartidor',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./repartidor.component.scss'],
})
export class RepartidorComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }

  ngOnInit(): void {
    this.vm.listRepartidor();
  }
}


export const REPARTIDOR_COMPONENTES = [
  RepartidorComponent,
];
