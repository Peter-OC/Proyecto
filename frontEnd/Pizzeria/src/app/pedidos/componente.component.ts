import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { PedidosViewModelService } from './servicios.service';

@Component({
  selector: 'app-pedidos',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
})
export class PedidosComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}
@Component({
  selector: 'app-pedidos-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.css'],
})
export class PedidosListComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}
@Component({
  selector: 'app-pedidos-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
})
export class PedidosAddComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.add();
  }
}
@Component({
  selector: 'app-pedidos-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
})
export class PedidosEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: PedidosViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe((params: ParamMap) => {
      const id = parseInt(params?.get('id') ?? '');
      if (id) {
        this.vm.edit(id);
      } else {
        this.router.navigate(['/404.html']);
      }
    });
  }
  ngOnDestroy(): void {
    this.obs$.unsubscribe();
  }
}
@Component({
  selector: 'app-pedidos-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.css'],
})
export class PedidosViewComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: PedidosViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe((params: ParamMap) => {
      const id = parseInt(params?.get('id') ?? '');
      if (id) {
        this.vm.view(id);
      } else {
        this.router.navigate(['/404.html']);
      }
    });
  }
  ngOnDestroy(): void {
    this.obs$.unsubscribe();
  }
}

export const PEDIDOS_COMPONENTES = [
  PedidosComponent,
  PedidosListComponent,
  PedidosAddComponent,
  PedidosEditComponent,
  PedidosViewComponent,
];