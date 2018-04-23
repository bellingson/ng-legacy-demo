import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import {ProductService} from "./product.service";

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.scss']
})
export class ProductUpdateComponent implements OnInit {

  product: any;

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      const id = +params['id'];
      this.fetchProduct(id);
    })


  }

  fetchProduct(id: number) {
     this.productService.get(id)
          .subscribe(product => this.product = product);
  }

  updateProduct() {
    this.productService.update(this.product)
      .subscribe(r => {
          this.router.navigateByUrl('/admin/product');
      });
  }

  deleteProduct() {

    if(!confirm("Are you sure you want to delete this?"))
      return;

    this.productService.delete(this.product)
      .subscribe(r => {
        this.router.navigateByUrl('/admin/product');
      });

  }




}
