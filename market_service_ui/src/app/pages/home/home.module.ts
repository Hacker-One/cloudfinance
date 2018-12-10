import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../../shared/shared.module';
import { HomeComponent } from './home.component';
import {ProductComponent} from '../products/product/product.component';
import {ProductsModule} from '../products/products.module';
import {ProductsComponent} from '../products/products.component';


export const routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' ,children:
          [
              { path: '', component:ProductsComponent, data: { breadcrumb: 'All Products' } },
              { path: ':id/:name', component: ProductComponent ,data: { breadcrumb: '商品详情' }}
          ]
  }
];

@NgModule({
  imports: [
    CommonModule,
    ProductsModule,
    RouterModule.forChild(routes),
    SharedModule

  ],
  declarations: [
    HomeComponent
  ]
})
export class HomeModule { }
