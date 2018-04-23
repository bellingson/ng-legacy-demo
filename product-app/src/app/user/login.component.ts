import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

import {AuthService} from "./auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
  }

  login(value) {

    this.authService.login(value.email, value.password)
      .subscribe(r => {
        this.router.navigateByUrl('/admin/product');
      }, err => {
        console.log(err);
      });

  }

}
