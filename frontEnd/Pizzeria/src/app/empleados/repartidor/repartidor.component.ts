import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { PedidosInProcessViewModelService, PedidosReadyViewModelService, PedidosSentViewModelService, PedidosViewModelService } from '../servicios.service';

@Component({
  selector: 'app-repartidor',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./repartidor.component.scss'],
})
export class RepartidorComponent implements OnInit {
  constructor(protected vm: PedidosReadyViewModelService, protected vm2: PedidosSentViewModelService) {}
  public get VM(): PedidosReadyViewModelService {
    return this.vm;
  }
  public get VM2(): PedidosSentViewModelService {
    return this.vm2;
  }
  ngOnInit(): void {
    this.vm.list();
    this.vm2.list();
  }
}

@Component({
  selector: 'app-repartidor-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./repartidor.component.scss'],
})
export class RepartidorListComponent implements OnInit {
  constructor(protected vm: PedidosReadyViewModelService, protected vm2: PedidosSentViewModelService) {}
  public get VM(): PedidosReadyViewModelService {
    return this.vm;
  }
  public get VM2(): PedidosSentViewModelService {
    return this.vm2;
  }

  ngOnInit(): void {
    this.vm.list();
    this.vm2.list();
  }
}

@Component({
  selector: 'app-repartidor-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./repartidor.component.scss'],
})
export class RepartidorAddComponent implements OnInit {
  constructor(protected vm: PedidosReadyViewModelService, protected vm2: PedidosSentViewModelService) {}
  public get VM(): PedidosReadyViewModelService {
    return this.vm;
  }
  public get VM2(): PedidosSentViewModelService {
    return this.vm2;
  }
  ngOnInit(): void {
    this.vm.add();
    this.vm2.add();
  }
}

@Component({
  selector: 'app-repartidor-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./repartidor.component.scss'],
})
export class RepartidorEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: PedidosReadyViewModelService,
    protected vm2: PedidosSentViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): PedidosReadyViewModelService {
    return this.vm;
  }
  public get VM2(): PedidosSentViewModelService {
    return this.vm2;
  }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe((params: ParamMap) => {
      const id = parseInt(params?.get('id') ?? '');
      if (id) {
        this.vm.edit(id);
        this.vm2.edit(id);
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
  selector: 'app-repartidor-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./repartidor.component.scss'],
})
export class RepartidorViewComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: PedidosReadyViewModelService,
    protected vm2: PedidosSentViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): PedidosReadyViewModelService {
    return this.vm;
  }

  public get VM2(): PedidosSentViewModelService {
    return this.vm2;
  }

  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe((params: ParamMap) => {
      const id = parseInt(params?.get('id') ?? '');
      if (id) {
        this.vm.view(id);
        this.vm2.view(id);
      } else {
        this.router.navigate(['/404.html']);
      }
    });
  }
  ngOnDestroy(): void {
    this.obs$.unsubscribe();
  }
}

export const REPARTIDOR_COMPONENTES = [
  RepartidorComponent,
  RepartidorListComponent,
  RepartidorAddComponent,
  RepartidorEditComponent,
  RepartidorViewComponent,
];
