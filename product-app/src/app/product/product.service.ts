import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable()
export class ProductService {

  baseUrl = '/api/admin/product';

  constructor(private http: HttpClient) { }

  query() : Observable<Array<any>> {
    return this.http.get<Array<any>>(this.baseUrl);
  }

  get(id: number) : Observable<any> {
    return this.http.get<any>(this.baseUrl + '/' + id);
  }

  add(product: any) : Observable<any> {
    return this.http.post<any>(this.baseUrl, product);
  }

  update(product: any) : Observable<any> {
    return this.http.put<any>(this.baseUrl + '/' + product.id, product);
  }

  delete(product: any) : Observable<any> {
    return this.http.delete<any>(this.baseUrl + '/' + product.id);
  }

}
