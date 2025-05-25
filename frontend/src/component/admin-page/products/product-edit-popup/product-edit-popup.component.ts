import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProductService } from '../../../../service/product.service';
import { Product } from '../../../../model/product';

interface DialogData {
  product: Product;
  categoryId: number;
}

@Component({
  standalone: false,
  selector: 'shop-product-edit-popup',
  templateUrl: './product-edit-popup.component.html',
  styleUrl: './product-edit-popup.component.scss'
})
export class ProductEditPopupComponent {
  productForm: FormGroup;
  categoryId: number;
  product: Product;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ProductEditPopupComponent>,
    private productService: ProductService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {
    this.product = data.product;
    this.categoryId = data.categoryId;

    // Initialize the form with product data
    this.productForm = this.fb.group({
      id: [this.product.id],
      title: [this.product.title, Validators.required],
      image: [this.product.image, Validators.required],
      description: [this.product.description, Validators.required],
      price: [this.product.price, [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      const updatedProduct: Product = this.productForm.value;

      this.productService.update(this.categoryId, updatedProduct).subscribe({
        next: () => {
          this.dialogRef.close(true);
          this.snackBar.open('Ապրանքը հաջողությամբ թարմացվել է', 'Փակել', {
            duration: 3000
          });
        },
        error: (error) => {
          console.error('Error updating product:', error);
          this.snackBar.open('Ապրանքը թարմացնելը ձախողվեց', 'Փակել', {
            duration: 3000
          });
        }
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }

  onFileUploaded(filePath: string | undefined): void {
    this.productForm.get('image')?.setValue(filePath);
    this.productForm.get('image')?.markAsDirty();
  }
}
