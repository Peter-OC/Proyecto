import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { IngredientesViewModelService } from './servicios.service';

@Component({
  selector: 'app-ingredientes',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class IngredientesComponent implements OnInit {
  constructor(protected vm: IngredientesViewModelService) {}
  public get VM(): IngredientesViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
}
@Component({
  selector: 'app-ingredientes-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class IngredientesListComponent implements OnInit {
  constructor(protected vm: IngredientesViewModelService, private messageService: MessageService, private primengConfig: PrimeNGConfig, private myService: IngredientesViewModelService,) {}
  public get VM(): IngredientesViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
  onConfirm() {
    this.myService.si();
    this.messageService.clear('c');
}

  onReject() {
      this.messageService.clear('c');
  }
}
@Component({
  selector: 'app-ingredientes-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class IngredientesAddComponent implements OnInit {
  constructor(protected vm: IngredientesViewModelService) {}
  public get VM(): IngredientesViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.add();
  }
}
@Component({
  selector: 'app-ingredientes-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class IngredientesEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: IngredientesViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): IngredientesViewModelService {
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
  selector: 'app-ingredientes-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class IngredientesViewComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(
    protected vm: IngredientesViewModelService,
    protected route: ActivatedRoute,
    protected router: Router,
    private messageService: MessageService, private primengConfig: PrimeNGConfig, private myService: IngredientesViewModelService,
  ) {}
  public get VM(): IngredientesViewModelService {
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
  onConfirm() {
    this.myService.si();
    this.messageService.clear('c');
}

  onReject() {
      this.messageService.clear('c');
  }
}

export const INGREDIENTES_COMPONENTES = [
  IngredientesComponent,
  IngredientesListComponent,
  IngredientesAddComponent,
  IngredientesEditComponent,
  IngredientesViewComponent,
];
