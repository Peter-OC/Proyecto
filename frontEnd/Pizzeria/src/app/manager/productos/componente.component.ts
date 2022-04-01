import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { ProductosViewModelService } from './servicios.service';

@Component({
  selector: 'app-productos',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class ProductosComponent implements OnInit {
  constructor(protected vm: ProductosViewModelService) {}
  public get VM(): ProductosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}
@Component({
  selector: 'app-productos-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class ProductosListComponent implements OnInit {
  constructor(protected vm: ProductosViewModelService) {}
  public get VM(): ProductosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}
@Component({
  selector: 'app-productos-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class ProductosAddComponent implements OnInit {
  constructor(protected vm: ProductosViewModelService) {}
  public get VM(): ProductosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.add();
  }
}
@Component({
  selector: 'app-productos-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class ProductosEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: ProductosViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): ProductosViewModelService {
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
  selector: 'app-productos-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class ProductosViewComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: ProductosViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): ProductosViewModelService {
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

export const PRODUCTOS_COMPONENTES = [
  ProductosComponent,
  ProductosListComponent,
  ProductosAddComponent,
  ProductosEditComponent,
  ProductosViewComponent,
];
