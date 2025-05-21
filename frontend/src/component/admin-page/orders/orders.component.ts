import {Component, OnInit} from '@angular/core';
import {OrderService} from '../../../service/order.service';
import {Order} from '../../../model/order';
import {ActivatedRoute} from '@angular/router';

@Component({
  standalone: false,
  selector: 'shop-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  orders: Order[] = [];
  filteredOrders: Order[] = [];

  showDone: boolean = true;
  showInProgress: boolean = true;

  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
        this.showDone = params['done'] === 'true' || params['done'] === undefined;
        this.showInProgress = params['inProgress'] === 'true' || params['inProgress'] === undefined;

      if (this.orders.length > 0) {
        this.applyFilters();
      } else {
        this.loadOrders();
      }
    });
  }

  loadOrders(): void {
    this.orderService.orders().subscribe(orders => {
      // Sort orders by id
      this.orders = orders.sort((a, b) => a.id - b.id);
      this.applyFilters();
    });
  }

  applyFilters(): void {
    this.filteredOrders = this.orders.filter(order => {
      if (this.showDone && order.done) {
        return true;
      }
      else if (this.showInProgress && !order.done) {
        return true;
      }
      return false;
    });
  }

  deleteOrder(id: number): void {
    this.orderService.delete(id).subscribe(() => this.loadOrders());
  }

  markOrderAsDone(id: number): void {
    this.orderService.markAsDone(id).subscribe(() => this.loadOrders());
  }
}
