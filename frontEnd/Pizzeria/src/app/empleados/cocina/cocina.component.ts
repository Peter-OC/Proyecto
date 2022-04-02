import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { PedidosInProcessViewModelService, PedidosViewModelService } from '../servicios.service';

@Component({
  selector: 'app-cocina',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService, protected vm2: PedidosInProcessViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  public get VM2(): PedidosInProcessViewModelService {
    return this.vm2;
  }
  ngOnInit(): void {
    this.vm.list();
    this.vm2.list();
  }
}

@Component({
  selector: 'app-cocina-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaListComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService, protected vm2: PedidosInProcessViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  public get VM2(): PedidosInProcessViewModelService {
    return this.vm2;
  }
  ngOnInit(): void {
    this.vm.list();
    this.vm2.list();
  }
}

@Component({
  selector: 'app-cocina-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaAddComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService, protected vm2: PedidosInProcessViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  public get VM2(): PedidosInProcessViewModelService {
    return this.vm2;
  }
  ngOnInit(): void {
    this.vm.add();
    this.vm2.add();
  }
}

@Component({
  selector: 'app-cocina-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: PedidosViewModelService,
    protected vm2: PedidosInProcessViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  public get VM2(): PedidosInProcessViewModelService {
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
  selector: 'app-cocina-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaViewComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: PedidosViewModelService,
    protected vm2: PedidosInProcessViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }

  public get VM2(): PedidosInProcessViewModelService {
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

export const COCINA_COMPONENTES = [
  CocinaComponent,
  CocinaListComponent,
  CocinaAddComponent,
  CocinaEditComponent,
  CocinaViewComponent,
];
