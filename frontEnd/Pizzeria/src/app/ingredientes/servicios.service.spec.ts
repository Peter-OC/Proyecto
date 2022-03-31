import { TestBed } from '@angular/core/testing';

import { IngredientesViewModelService } from './servicios.service';

describe('ServiciosService', () => {
  let service: IngredientesViewModelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IngredientesViewModelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
