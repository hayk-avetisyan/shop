import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ProductCategory} from '../../../model/product-category';

@Component({
  standalone: false,
  selector: 'shop-category-popup',
  templateUrl: './category-popup.component.html',
  styleUrl: './category-popup.component.scss'
})
export class CategoryPopupComponent {

  constructor(
    private dialogRef: MatDialogRef<CategoryPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public category: ProductCategory
  ) {
  }

  close() {
    this.dialogRef.close()
  }

}
