import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { DatosUsuariosViewModelService } from './servicios.service';

@Component({
  selector: 'app-datos-usuarios-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.scss'],
})
export class DatosUsuariosEditComponent implements OnInit {
  private obs$: any;
  constructor(
    protected vm: DatosUsuariosViewModelService,
    protected route: ActivatedRoute,
    protected router: Router
  ) {}
  public get VM(): DatosUsuariosViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.VM.edit();
  }
}

export const USUARIOS_COMPONENTES = [
  DatosUsuariosEditComponent,
];
