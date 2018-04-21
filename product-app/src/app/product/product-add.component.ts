import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.scss']
})
export class ProductAddComponent implements OnInit {

  @Output() newProduct = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  addProduct(product) {
    this.newProduct.emit(product);
  }

}
