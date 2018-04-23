import { Component, OnInit } from '@angular/core';
import {AuthService} from "./user/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  user: any;

  constructor(private authService: AuthService) {
    this.authService.user.subscribe(user => this.user = user);
  }

  ngOnInit() {
  }

}
