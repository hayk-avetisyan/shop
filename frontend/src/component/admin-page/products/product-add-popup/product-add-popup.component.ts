import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProductService } from '../../../../service/product.service';
import { Product } from '../../../../model/product';

@Component({
  standalone: false,
  selector: 'shop-product-add-popup',
  templateUrl: './product-add-popup.component.html',
  styleUrl: './product-add-popup.component.scss'
})
export class ProductAddPopupComponent {
  productForm: FormGroup;
  categoryId: number;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ProductAddPopupComponent>,
    private productService: ProductService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: number
  ) {
    this.categoryId = data;

    // Initialize the form with empty values
    this.productForm = this.fb.group({
      title: ['', Validators.required],
      image: ['', Validators.required],
      description: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      const newProduct: Product = this.productForm.value;

      this.productService.add(this.categoryId, newProduct).subscribe({
        next: () => {
          this.dialogRef.close(true);
          this.snackBar.open('Ապրանքը հաջողությամբ ավելացվել է', 'Փակել', {
            duration: 3000
          });
        },
        error: (error) => {
          console.error('Error adding product:', error);
          this.snackBar.open('Ապրանքը ավելացնելը ձախողվեց', 'Փակել', {
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
