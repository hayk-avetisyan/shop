import {Product} from './product';

export interface Order {
  id: number,
  items: OrderItem[]
  contact: {
    name: string,
    phone: string,
  }
  done: boolean
  price: number
}

export interface OrderItem {
  product: Product,
  quantity: number,
}
