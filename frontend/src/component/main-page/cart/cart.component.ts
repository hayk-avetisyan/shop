import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {PaymentPopupComponent} from '../payment-popup/payment-popup.component';
import {CartItem} from '../../../model/cart-item';
import {CartService} from '../../../service/cart.service';

@Component({
  standalone: false,
  selector: 'shop-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent implements OnInit {

  public items: CartItem[] = [];
  public totalPrice: number = 0;

  constructor(
    private cartService: CartService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit() {
    this.cartService.onChange().subscribe(items => {
      this.items = items;
      this.totalPrice = items.map(item => item.product.price * item.quantity)
        .reduce((f, s) => f + s, 0)
    })
  }

  itemText(item: CartItem): string {
    return item.product.title
      + ' - ' + item.quantity + ' հատ'
      + ' - ' + (item.quantity * item.product.price) + ' դրամ'
  }

  removeItem(item: CartItem) {
    this.cartService.remove(item);
  }

  order() {

    if (this.items.length == 0) {
      return;
    }

    const dialog = this.dialog.open(
      PaymentPopupComponent, {width: '500px', panelClass: 'payment-popup', data: this.items}
    );

    dialog.afterClosed().subscribe(succeed => succeed && this.cartService.clear())
  }
}
