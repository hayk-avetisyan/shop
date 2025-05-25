import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ProductCategory, ProductCategoryMap} from '../model/product-category';
import {map, Observable} from 'rxjs';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  categories(): Observable<ProductCategory[]> {
    return this.http.get<ProductCategoryMap[]>('/api/categories', {responseType: 'json'}).pipe(
      map((categories: ProductCategoryMap[]) => this.convert(categories)),
    );
  }

  update(categoryId: number, product: Product): Observable<void> {
    return this.http.put<void>(`/api/categories/${categoryId}/products/${product.id}`, product)
  }

  remove(categoryId: number, productId: number): Observable<void> {
    return this.http.delete<void>(`/api/categories/${categoryId}/products/${productId}`)
  }

  private convert(categories: ProductCategoryMap[]): ProductCategory[] {
    return categories.map(category => {

      const products = Object.getOwnPropertyNames(category.products).map(id => category.products[id])

      return {
        id: category.id,
        title: category.title,
        description: category.description,
        coverImage: category.coverImage,
        coverVideo: category.coverVideo,
        products: products
      }
    })
  }
}
