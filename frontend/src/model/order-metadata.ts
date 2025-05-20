export interface OrderMetadata {
  items: OrderItemMetadata[]
  contact: {
    name: string,
    phone: string,
  }
  price: number
}

export interface OrderItemMetadata {
  productId: number,
  quantity: number,
}
