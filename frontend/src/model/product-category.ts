import {Product} from './product';

export interface ProductCategory {
  id: number,
  title: string,
  description: string,
  coverImage: string,
  coverVideo: string,
  products: Product[]
}

export interface ProductCategoryMap {
  id: number,
  title: string,
  description: string,
  coverImage: string,
  coverVideo: string,
  products: {[key: string]: Product}
}

