import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ProductService} from '../../../service/product.service';
import {ProductCategory} from '../../../model/product-category';
import {Product} from '../../../model/product';
import {PageEditPopupComponent} from './page-edit-popup/page-edit-popup.component';

@Component({
  standalone: false,
  selector: 'shop-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export class ProductsComponent implements OnInit {

  public categories: ProductCategory[] = [];
  public selectedCategory: ProductCategory | undefined = undefined;

  constructor(
    public productService: ProductService,
    public dialog: MatDialog
  ) {
  }

  public ngOnInit(): void {
    this.loadProducts();
  }

  get products(): Product[] {
    return this.selectedCategory ? this.selectedCategory.products : [];
  }

  remove(id: number) {
    if (!this.selectedCategory) {
      return
    }

    this.productService.remove(this.selectedCategory.id, id)
      .subscribe(() => this.loadProducts());
  }

  edit(product: Product) {
    if (!this.selectedCategory) {
      return;
    }

    this.dialog.open(PageEditPopupComponent, {
      width: '500px',
      minWidth: '500px',
      data: {
        product: product,
        categoryId: this.selectedCategory.id
      }
    })
      .afterClosed().subscribe(result => {
        if (result) {
          this.loadProducts();
        }
      });
  }

  private loadProducts() {
    this.productService.categories().subscribe(categories => {
      this.categories = categories;
      this.selectCategory(this.selectedCategory ? this.selectedCategory.id : categories[0].id);
    });
  }

  public selectCategory(selectedId: number) {
    this.selectedCategory = this.categories.find(category => category.id == selectedId);

    if (!this.selectedCategory) {
      this.selectedCategory = this.categories[0];
    }
  }
}
