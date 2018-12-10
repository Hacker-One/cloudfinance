import { Component, OnInit } from '@angular/core';
import { Data, AppService } from '../../../app.service';
import {LocalStorage} from '../../../local.storage';
import {MatSnackBar} from '@angular/material';
import {NavigationEnd, Router} from '@angular/router';

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html'
})
export class TopMenuComponent implements OnInit {
  public currencies = ['USD', 'EUR'];
  public currency:any;
  public user:any
  public flags = [
    { name:'English', image: 'assets/images/flags/gb.svg' },
    { name:'German', image: 'assets/images/flags/de.svg' },
    { name:'French', image: 'assets/images/flags/fr.svg' },
    { name:'Russian', image: 'assets/images/flags/ru.svg' },
    { name:'Turkish', image: 'assets/images/flags/tr.svg' }
  ]
  public flag:any;
  public isLoginButton=true;
  constructor(public router: Router,public appService:AppService,public localStorage:LocalStorage,public snackBar: MatSnackBar) { }

  ngOnInit() {
      if(location.pathname=='/sign-in'){
        this.isLoginButton=false;
      }
      this.router.events.subscribe(event => {
          if(event instanceof NavigationEnd) {
              if(event.url=='/sign-in'){
                  this.isLoginButton=false;
              }else{
                  this.isLoginButton=true;
              }
          }
      });

    this.currency = this.currencies[0];
    this.flag = this.flags[0];
  }
  public changeCurrency(currency){
    this.currency = currency;
  }

  public changeLang(flag){
    this.flag = flag;
  }
  public logout(){
    this.localStorage.remove('userName');
      this.snackBar.open('退出成功', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
      this.router.navigate(['/']);
  }


}
