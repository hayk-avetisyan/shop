import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../../model/product';
import {MatDialog,} from '@angular/material/dialog';
import {AddCartPopupComponent} from '../add-cart-popup/add-cart-popup.component';

@Component({
  standalone: false,
  selector: 'shop-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss'
})
export class ProductListComponent implements OnInit {

  public products: Product[] = []

  constructor(
    public productService: ProductService,
    public dialog: MatDialog
  ) {
  }

  public ngOnInit(): void {
    this.productService.products().subscribe(products => {
      this.products = products;
    });
  }

  addToChart(product: Product) {
    this.dialog.open(AddCartPopupComponent, {width: '200px', data: product})
  }

}
