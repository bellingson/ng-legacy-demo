import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ProductListComponent } from './product/product-list.component';
import {ProductService} from "./product/product.service";

import {appRoutes} from "./app.routing";
import { ProductAddComponent } from './product/product-add.component';
import { LoginComponent } from './user/login.component';
import {AuthService} from "./user/auth.service";
import { HomeComponent } from './home.component';
import {AuthInterceptor} from "./user/auth.interceptor";
import { SignOutComponent } from './user/sign-out.component';
import {AdminGuard} from "./user/admin.guard";
import { NavComponent } from './nav.component';
import { ProductUpdateComponent } from './product/product-update.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    ProductAddComponent,
    LoginComponent,
    HomeComponent,
    SignOutComponent,
    NavComponent,
    ProductUpdateComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    appRoutes
  ],
  providers: [ProductService,
              AuthService,
              { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
              AdminGuard
              ],
  bootstrap: [AppComponent]
})
export class AppModule { }
