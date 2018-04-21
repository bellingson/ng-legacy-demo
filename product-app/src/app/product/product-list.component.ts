import { Component, OnInit } from '@angular/core';
import {ProductService} from "./product.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  products: Array<any>;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.fetchProducts();
  }

  fetchProducts() {
     this.productService.query()
       .subscribe(products => this.products = products);
  }

  addProduct(product: any) {

    this.productService.add(product)
      .subscribe(r => {
          this.fetchProducts();
      });

  }

}
