import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCartPopupComponent } from './add-cart-popup.component';

describe('BasketComponent', () => {
  let component: AddCartPopupComponent;
  let fixture: ComponentFixture<AddCartPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddCartPopupComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCartPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
