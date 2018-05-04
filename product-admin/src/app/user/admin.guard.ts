import { Injectable } from '@angular/core';

import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

import {AuthService} from "./auth.service";

@Injectable()
export class AdminGuard implements CanActivate {

  user: any;

  constructor(private authService: AuthService,
              private router: Router) {
    this.authService.user.subscribe(user => this.user = user);
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    // console.log('can activate') ;

    if(this.user == null) {
      // console.log('can activate redirect') ;

      this.router.navigateByUrl('/login');
      return false;
    }

    const adminRole = this.user.roles.find( r => r == 'ROLE_ADMIN' );

    if(adminRole) {
      // console.log('can activate true') ;
      return true;
    }

    // console.log('can activate redirect');

    this.router.navigateByUrl('/login');
    return false;
  }



}
