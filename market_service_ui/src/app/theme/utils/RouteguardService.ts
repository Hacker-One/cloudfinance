import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Router } from "@angular/router";
import {LocalStorage} from '../../local.storage';
@Injectable()
export class RouteguardService implements CanActivate{

    constructor(
        private router: Router,public localStorage:LocalStorage
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{

            if (!this.localStorage.get('userName')) {
                // 未登录，跳转到login
                this.router.navigate(['/sign-in']);
                return false;
            }else{
                // 已登录，跳转到当前路由
                return true;
            }
    }

}
