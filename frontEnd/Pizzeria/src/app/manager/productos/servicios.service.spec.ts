import { TestBed } from '@angular/core/testing';

import { ProductosViewModelService } from './servicios.service';

describe('ServiciosService', () => {
  let service: ProductosViewModelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductosViewModelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
