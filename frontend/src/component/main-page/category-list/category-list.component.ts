import { OnInit } from '@angular/core'
import { Component } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {CategoryPopupComponent} from '../category-popup/category-popup.component';
import {ProductCategory} from '../../../model/product-category';
import {ProductService} from '../../../service/product.service';

@Component({
  standalone: false,
  selector: 'shop-category-list',
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.scss'
})
export class CategoryListComponent implements OnInit {

  public categories: ProductCategory[] = []

  constructor(
    private productService: ProductService,
    private dialog: MatDialog
  ) {
  }

  public ngOnInit(): void {
    this.productService.categories().subscribe(categories => {
      this.categories = categories.filter(category => category.products.length);
    });
  }

  openPopup(category: ProductCategory) {
    this.dialog.open(CategoryPopupComponent, {
      width: '100%',
      height: '100%',
      maxHeight: '100%',
      maxWidth: '100%',
      panelClass: 'category-popup',
      data: category
    })
  }
}
