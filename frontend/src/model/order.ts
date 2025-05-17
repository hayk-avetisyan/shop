export interface Order {
  items: OrderItem[]
  contact: {
    name: string,
    phone: string,
  }
  price: number
}

export interface OrderItem {
  productId: number,
  quantity: number,
}
