import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {CartService} from '../../../service/cart.service';
import {Product} from '../../../model/product';

@Component({
  standalone: false,
  selector: 'shop-cart-popup',
  templateUrl: './add-cart-popup.component.html',
  styleUrl: './add-cart-popup.component.scss'
})
export class AddCartPopupComponent {

  public quantity: number = 1;

  constructor(
    private basketService: CartService,
    private dialogRef: MatDialogRef<AddCartPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public product: Product
  ) {
  }

  close() {
    this.dialogRef.close()
  }

  addToBasket() {
    this.basketService.add({
      product: this.product,
      quantity: this.quantity,
    })
    this.dialogRef.close()
  }
}
