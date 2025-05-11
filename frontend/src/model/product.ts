import {Resource} from './resource';
import {Flavour} from './flavour';

export interface Product {
  id: number,
  title: string,
  description: string,
  cover: Resource,
  background: Resource,
  price: number,
  flavours: Flavour[]
}
