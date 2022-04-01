import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzacardComponent } from './pizzacard.component';

describe('PizzacardComponent', () => {
  let component: PizzacardComponent;
  let fixture: ComponentFixture<PizzacardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PizzacardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PizzacardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
