import { TestBed } from '@angular/core/testing';

import { PedidosViewModelService } from './servicios.service';

describe('PedidosViewModelService', () => {
  let service: PedidosViewModelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PedidosViewModelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
