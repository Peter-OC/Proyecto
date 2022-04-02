import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { PedidosViewModelService } from '../servicios.service';

@Component({
  selector: 'app-cocina',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}

@Component({
  selector: 'app-cocina-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaListComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}

@Component({
  selector: 'app-cocina-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaAddComponent implements OnInit {
  constructor(protected vm: PedidosViewModelService) {}
  public get VM(): PedidosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.add();
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
  selector: 'app-cocina-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./cocina.component.scss'],
})
export class CocinaViewComponent implements OnInit, OnDestroy {
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

export const COCINA_COMPONENTES = [
  CocinaComponent,
  CocinaListComponent,
  CocinaAddComponent,
  CocinaEditComponent,
  CocinaViewComponent,
];
