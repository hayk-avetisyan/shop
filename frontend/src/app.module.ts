import {NgModule, provideZoneChangeDetection} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {provideHttpClient, withXsrfConfiguration} from '@angular/common/http';
import {provideRouter, RouterModule} from '@angular/router';
import {MatDialogModule} from '@angular/material/dialog';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatCardModule} from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MainPageComponent} from './component/main-page/main-page.component';
import {AboutPageComponent} from './component/about-page/about-page.component';
import {ProductListComponent} from './component/main-page/product-list/product-list.component';
import {HeaderComponent} from './component/main-page/header/header.component';
import {AddCartPopupComponent} from './component/main-page/add-cart-popup/add-cart-popup.component';
import {CategoryListComponent} from './component/main-page/category-list/category-list.component';
import {CategoryPopupComponent} from './component/main-page/category-popup/category-popup.component';
import {AboutSectionComponent} from './component/about-page/about-section/about-section.component';
import {ContactFormComponent} from './component/about-page/contact-form/contact-form.component';
import {PaymentPopupComponent} from './component/main-page/payment-popup/payment-popup.component';
import {CartComponent} from './component/main-page/cart/cart.component';
import {AdminPageComponent} from './component/admin-page/admin-page.component';
import {AppComponent} from './component/app/app.component';
import {canActivateAdminPage, canActivateLoginPage} from './guards';
import {LoginComponent} from './component/admin-page/login/login.component';
import {OrdersComponent} from './component/admin-page/orders/orders.component';
import {MessagesComponent} from './component/admin-page/messages/messages.component';
import {FileUploadComponent} from './component/admin-page/file-upload/file-upload.component';
import {ProductsComponent} from './component/admin-page/products/products.component';
import {ProductEditPopupComponent} from './component/admin-page/products/product-edit-popup/product-edit-popup.component';
import {ProductAddPopupComponent} from './component/admin-page/products/product-add-popup/product-add-popup.component';

@NgModule({
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatSnackBarModule,
    MatMenuModule,
    MatButtonModule,
    MatToolbarModule,
    MatExpansionModule,
    MatCardModule,
    MatCheckboxModule,
    MatIconModule,
    MatProgressBarModule,
    MatFormFieldModule,
    MatInputModule,
    RouterModule
  ],
  declarations: [
    AppComponent,
    HeaderComponent,
    ProductListComponent,
    AddCartPopupComponent,
    CategoryListComponent,
    CategoryPopupComponent,
    AboutSectionComponent,
    ContactFormComponent,
    PaymentPopupComponent,
    MainPageComponent,
    AboutPageComponent,
    CartComponent,
    AdminPageComponent,
    LoginComponent,
    OrdersComponent,
    MessagesComponent,
    FileUploadComponent,
    ProductsComponent,
    ProductEditPopupComponent,
    ProductAddPopupComponent
  ],
  bootstrap: [AppComponent],
  providers: [
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter([
      {path: '', component: MainPageComponent},
      {path: 'about', component: AboutPageComponent},
      {
        path: 'admin/login',
        component: LoginComponent,
        canActivate: [canActivateLoginPage]
      },
      {
        path: 'admin',
        component: AdminPageComponent,
        canActivate: [canActivateAdminPage],
        children: [
          {path: 'orders', component: OrdersComponent},
          {path: 'messages', component: MessagesComponent},
          {path: 'products', component: ProductsComponent},
          {path: '**', pathMatch: 'full', redirectTo: 'orders'},
        ]
      },
      {path: '**', pathMatch: 'full', redirectTo: ''},
    ]),
    provideHttpClient(
      withXsrfConfiguration({
        headerName: 'X-XSRF-TOKEN',
        cookieName: 'XSRF-TOKEN'
      })
    ),
  ]
})
export class AppModule {
}
