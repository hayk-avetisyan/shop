import {Injectable} from '@angular/core';
import {Observable, ReplaySubject} from 'rxjs';
import {CartItem} from '../model/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private items: CartItem[] = []
  private itemSubject: ReplaySubject<CartItem[]> = new ReplaySubject(1)

  onChange(): Observable<CartItem[]> {
    return this.itemSubject.asObservable()
  }

  add(newItem: CartItem) {

    let item = this.items.find(item => item.product.id == newItem.product.id)

    if (item) {
      item.quantity += newItem.quantity;
    } else {
      this.items.push(newItem)
    }

    this.itemSubject.next([...this.items]);
  }

  remove(itemToRemove: CartItem) {

    const newItems: CartItem[] = []

    for (const item of this.items) {
      const matches = item.product.id == itemToRemove.product.id
      if (!matches) {
        newItems.push(item);
      }
    }

    this.items = newItems;
    this.itemSubject.next(this.items);
  }

  clear() {
    this.items = []
    this.itemSubject.next(this.items)
  }
}
