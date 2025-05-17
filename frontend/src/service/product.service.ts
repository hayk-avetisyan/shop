import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ProductCategory} from '../model/product-category';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  products(): Observable<ProductCategory[]> {
    return this.http.get<ProductCategory[]>('/api/products', {responseType: 'json'});
  }
}
