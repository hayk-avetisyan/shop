import { Component } from '@angular/core';
import {CartService} from '../../service/cart.service';

@Component({
  standalone: false,
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent {

  constructor(private cartService: CartService) {
  }

}
