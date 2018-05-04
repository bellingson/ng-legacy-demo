import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

import {AuthService} from "./auth.service";

@Component({
  selector: 'app-sign-out',
  templateUrl: './sign-out.component.html',
  styleUrls: ['./sign-out.component.scss']
})
export class SignOutComponent implements OnInit {

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {

    this.authService.logout()
      .subscribe(r => {
        // this.router.navigateByUrl('/');
      });



  }



}
