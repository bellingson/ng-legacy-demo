import { Routes, RouterModule } from '@angular/router';
import {ProductListComponent} from "./product/product-list.component";
import {LoginComponent} from "./user/login.component";
import {HomeComponent} from "./home.component";
import {SignOutComponent} from "./user/sign-out.component";
import {AdminGuard} from "./user/admin.guard";
import {ProductUpdateComponent} from "./product/product-update.component";

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signout', component: SignOutComponent },
  { path: 'admin', canActivate: [ AdminGuard ], children: [
      { path: '', redirectTo: 'product', pathMatch: 'full' },
      { path: 'product', component: ProductListComponent },
      { path: ':id', component: ProductUpdateComponent }
    ]
  }
];

export const appRoutes = RouterModule.forRoot(routes, { useHash: true } );
