import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { CatalogoViewModelService } from './servicios.service';

@Component({
  selector: 'app-catalogo',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class CatalogoComponent implements OnInit {
  constructor(protected vm: CatalogoViewModelService) {}
  public get VM(): CatalogoViewModelService {
    return this.vm;
  }
  ngOnInit(): void {}
}

@Component({
  selector: 'app-catalogo-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class CatalogoListComponent implements OnInit {
  constructor(protected vm: CatalogoViewModelService) {}
  public get VM(): CatalogoViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}
@Component({
  selector: 'app-catalogo-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class CatalogoAddComponent implements OnInit {
  constructor(protected vm: CatalogoViewModelService) {}
  public get VM(): CatalogoViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.add();
  }
}
@Component({
  selector: 'app-catalogo-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class CatalogoEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: CatalogoViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): CatalogoViewModelService {
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
  selector: 'app-catalogo-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class CatalogoViewComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: CatalogoViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): CatalogoViewModelService {
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

export const CATALOGO_COMPONENTES = [
  CatalogoComponent,
  CatalogoListComponent,
  CatalogoAddComponent,
  CatalogoEditComponent,
  CatalogoViewComponent,
];
