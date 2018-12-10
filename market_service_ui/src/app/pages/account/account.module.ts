import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { AccountComponent } from './account.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { InformationComponent } from './information/information.component';
import { AddressesComponent } from './addresses/addresses.component';
import { OrdersComponent } from './orders/orders.component';
import {NgxPaginationModule} from 'ngx-pagination';
import {RouteguardService} from '../../theme/utils/RouteguardService';

export const routes = [
  {
      path: '',
      component: AccountComponent, children: [
          // { path: '', redirectTo: 'orders', pathMatch: 'full' },
          // { path: 'dashboard', component: DashboardComponent, data: {  breadcrumb: 'Dashboard' } },
          // { path: 'information', component: InformationComponent, data: {  breadcrumb: 'Information' } },
          // { path: 'addresses', component: AddressesComponent, data: {  breadcrumb: 'Addresses' } },
          { path: '', component: OrdersComponent,canActivate: [RouteguardService], data: {  breadcrumb: '订单' } }
      ]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ReactiveFormsModule,
    SharedModule,
    NgxPaginationModule
  ],
  declarations: [
    AccountComponent,
    DashboardComponent,
    InformationComponent,
    AddressesComponent,
    OrdersComponent
  ]
})
export class AccountModule { }
