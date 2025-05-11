import { Injectable } from '@angular/core';
import { ReplaySubject, Observable } from 'rxjs';
import {BasketItem} from '../model/basket-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private items: Map<string, number> = new Map()
  private itemSubject: ReplaySubject<Map<string, number>> = new ReplaySubject(1)

  onChange(): Observable<Map<string, number>> {
    return this.itemSubject.asObservable()
  }

  add(newItem: BasketItem) {

    const key = newItem.product.id + '-' + (newItem.flavourId ? newItem.flavourId : '')
    const quantity = this.items.get(key)

    quantity && this.items.set(key, quantity + 1)
    !quantity && this.items.set(key,1)

    this.itemSubject.next(this.items);
  }

  remove(key: string) {

    let quantity = this.items.get(key)
    if (quantity) {
      quantity--;
      quantity < 1 && this.items.delete(key)
      quantity > 0 && this.items.set(key, quantity)

      this.itemSubject.next(this.items);
    }
  }
}
