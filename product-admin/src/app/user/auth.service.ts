import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, Subject, ReplaySubject } from 'rxjs';
import 'rxjs/add/observable/concat';
import 'rxjs/add/observable/forkJoin';
import 'rxjs/add/observable/of';

const TOKEN = 'token';
const USER = 'user';

@Injectable()
export class AuthService {

  private _token = new ReplaySubject<string>(1);

  user = new ReplaySubject(1);

  constructor(private http: HttpClient) {
    this.initialize();
  }


  initialize() {

    const token = localStorage.getItem(TOKEN);
    const user = localStorage.getItem(USER);
    if(token && user) {
      this._token.next(token);
      this.user.next(JSON.parse(user));
    }

    this._token.subscribe(token => {

        if(token) {
           localStorage.setItem(TOKEN, token);
        } else {
          localStorage.removeItem(TOKEN);
        }

      });

    this.user.subscribe(user => {

      if(user) {
        localStorage.setItem(USER, JSON.stringify(user));
      } else {
        localStorage.removeItem(USER);
      }

    });


  }


  login(username: string, password: string) : Observable<boolean> {

    let resp = new Subject<boolean>();

    username = encodeURIComponent(username);
    password = encodeURIComponent(password);

    const body = `username=${username}&password=${password}`;

    const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

    Observable.concat(
        this.http.post('/login', body, { headers: headers, responseType: 'text'}),
        this.http.get('/api/member/current')
      )
      .toArray()
      .subscribe(data => {

          const r: any = data[1];
          const token = r.token;
          const user = r.user;

          this._token.next(token);
          this.user.next(user);

          resp.next(true);
          resp.complete();

      }, err => {

        resp.error(err);
        resp.complete();
      });

    return resp;
  }

  logout() : Observable<boolean> {

    const resp = new Subject<boolean>();

    this.http.get('/signout', { responseType: 'text' })
      .subscribe(r => {
        this._token.next(null);
        this.user.next(null);
        resp.next(true);
        resp.complete();

      }, er => {
        resp.error(er);
        resp.complete();
      });

    return resp;
  }

  get token() : string {
     let _token;
     this._token.subscribe(token => _token = token);
     return _token;
  }



}
