export interface OrderMetadata {
  items: OrderItemMetadata[]
  contact: {
    name: string,
    phone: string,
    address: string,
  }
  price: number
}

export interface OrderItemMetadata {
  productId: number,
  quantity: number,
}
