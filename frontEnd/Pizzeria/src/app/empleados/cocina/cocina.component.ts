import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { PedidosViewModelService } from '../servicios.service';

@Component({
  selector: 'app-cocina',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }

  ngOnInit(): void {
    this.vm.listCocina();
  }
}

export const COCINA_COMPONENTES = [
  CocinaComponent,
];
