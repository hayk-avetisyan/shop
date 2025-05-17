import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Order} from '../model/order';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
  }

  placeOrder(order: Order): Observable<boolean> {
    return this.http.post<boolean>('/api/orders', order)
  }
}
