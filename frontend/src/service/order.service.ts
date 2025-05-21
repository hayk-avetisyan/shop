import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {OrderMetadata} from '../model/order-metadata';
import {Observable} from 'rxjs';
import {Order} from '../model/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
  }

  orders(): Observable<Order[]> {
    return this.http.get<Order[]>('/api/orders')
  }

  placeOrder(order: OrderMetadata): Observable<void> {
    return this.http.post<void>('/api/orders', order)
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`/api/orders/${id}`)
  }

  markAsDone(id: number): Observable<void> {
    return this.http.patch<void>(`/api/orders/${id}/done`, null)
  }
}
