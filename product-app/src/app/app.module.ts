import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ProductListComponent } from './product/product-list.component';
import {ProductService} from "./product/product.service";

import {appRoutes} from "./app.routing";
import { ProductAddComponent } from './product/product-add.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    ProductAddComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    appRoutes
  ],
  providers: [ProductService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
