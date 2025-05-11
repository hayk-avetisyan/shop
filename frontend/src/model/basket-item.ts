import {Product} from './product';

export interface BasketItem {
  product: Product,
  flavourId: number | undefined,
  quantity: number
}
