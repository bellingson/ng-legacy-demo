import { Component } from '@angular/core';

import {AuthService} from "./user/auth.service";

import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent {

  user: any;

  constructor(private authService: AuthService,
              private router: Router) {
    this.authService.user.subscribe(user => this.user = user);
  }

  signOut() {

    if(!confirm("Are you sure you want to sign out?"))
      return;

    this.authService.logout()
      .subscribe(r => {
        this.router.navigateByUrl('/');
      });
  }


}
