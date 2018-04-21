import { Routes, RouterModule } from '@angular/router';
import {ProductListComponent} from "./product/product-list.component";

const routes: Routes = [
  { path: '', redirectTo: '/admin/product', pathMatch: 'full' },
  { path: 'admin', children: [
      { path: '', redirectTo: 'product', pathMatch: 'full' },
      { path: 'product', component: ProductListComponent }
    ]
  }
];

export const appRoutes = RouterModule.forRoot(routes, { useHash: true } );
