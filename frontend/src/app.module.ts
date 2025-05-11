import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import {AppComponent} from './component/app/app.component';
import {HeaderComponent} from './component/header/header.component';
import {ProductListComponent} from './component/product-list/product-list.component';
import {ProductService} from './service/product.service';
import { provideRouter } from '@angular/router';
import { provideZoneChangeDetection } from '@angular/core';
import {MatDialogModule} from '@angular/material/dialog';
import {FormsModule} from '@angular/forms';
import {AddCartPopupComponent} from './component/add-cart-popup/add-cart-popup.component';
import {CartComponent} from './component/cart/cart.component';

@NgModule({
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    MatDialogModule
  ],
  declarations: [
    AppComponent,
    HeaderComponent,
    ProductListComponent,
    AddCartPopupComponent,
    CartComponent
  ],
  bootstrap: [AppComponent],
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter([]),
    provideHttpClient(),
    ProductService
  ]
})
export class AppModule { }
