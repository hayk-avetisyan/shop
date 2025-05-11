import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Product} from '../../model/product';
import {CartService} from '../../service/cart.service';

@Component({
  standalone: false,
  selector: 'app-basket',
  templateUrl: './add-cart-popup.component.html',
  styleUrl: './add-cart-popup.component.scss'
})
export class AddCartPopupComponent {

  public quantity: number = 1;
  public flavourId: string | undefined

  constructor(
    private basketService: CartService,
    private dialogRef: MatDialogRef<AddCartPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public product: Product
  ) {
    this.flavourId = product.flavours.length ? String(product.flavours[0].id) : undefined
  }

  close() {
    this.dialogRef.close()
  }

  addToBasket() {
    this.basketService.add({
      product: this.product,
      flavourId: this.flavourId ? Number(this.flavourId) : undefined,
      quantity: this.quantity
    })
    this.dialogRef.close()
  }
}
