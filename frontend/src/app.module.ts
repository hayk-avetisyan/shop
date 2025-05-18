import {NgModule, provideZoneChangeDetection} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {provideHttpClient, withXsrfConfiguration} from '@angular/common/http';
import {provideRouter, RouterModule} from '@angular/router';
import {MatDialogModule} from '@angular/material/dialog';
import {FormsModule} from '@angular/forms';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
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
import {canActivateAdminPage} from './guards';
import {LoginComponent} from './component/admin-page/login/login.component';

@NgModule({
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    MatDialogModule,
    MatSnackBarModule,
    MatMenuModule,
    MatButtonModule,
    MatToolbarModule,
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
    LoginComponent
  ],
  bootstrap: [AppComponent],
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter([
      {path: '', component: MainPageComponent},
      {path: 'about', component: AboutPageComponent},
      {path: 'admin/login', component: LoginComponent},
      {
        path: 'admin',
        component: AdminPageComponent,
        canActivate: [canActivateAdminPage],
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
export class AppModule { }
