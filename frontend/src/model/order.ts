export interface Order {
  id: number,
  items: OrderItem[]
  contact: {
    name: string,
    phone: string,
  }
  done?: boolean
  price: number
}

export interface OrderItem {
  productId: number,
  quantity: number,
}
