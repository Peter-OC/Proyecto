import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { UsuariosViewModelService } from './servicios.service';

@Component({
  selector: 'app-usuarios',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class UsuariosComponent implements OnInit {
  constructor(protected vm: UsuariosViewModelService) {}
  public get VM(): UsuariosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}
@Component({
  selector: 'app-usuarios-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class UsuariosListComponent implements OnInit {
  constructor(protected vm: UsuariosViewModelService) {}
  public get VM(): UsuariosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}
@Component({
  selector: 'app-usuarios-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class UsuariosAddComponent implements OnInit {
  constructor(protected vm: UsuariosViewModelService) {}
  public get VM(): UsuariosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.add();
  }
}
@Component({
  selector: 'app-usuarios-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class UsuariosEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: UsuariosViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): UsuariosViewModelService {
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
  selector: 'app-usuarios-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class UsuariosViewComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: UsuariosViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): UsuariosViewModelService {
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

export const USUARIOS_COMPONENTES = [
  UsuariosComponent,
  UsuariosListComponent,
  UsuariosAddComponent,
  UsuariosEditComponent,
  UsuariosViewComponent,
];
