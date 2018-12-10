import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material';
import {HttpInterceptorService} from '../../../theme/utils/httpInterceptor.service';
import {LocalStorage} from '../../../local.storage';
import {Router} from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  public page:any
  public count:any;
  public total=0
  public orders = []
  constructor( public router:Router,public localStorage:LocalStorage,public snackBar: MatSnackBar,public httpUtil:HttpInterceptorService) { }

  ngOnInit() {

          this.count =12;
          this.getAllOrders("",1)

  }
    public getAllOrders(name,page){
        this.httpUtil.get('/v1/orders?name='+name+'&page='+page,data=>{
            if(data.statusCode=="200"){
                if(data.code=="000"){
                    this.total=data.data['size:']
                    this.orders=data.data['orders:'];
                }else{
                    this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                }
            }else{
                this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
            }
        },err=>{
            this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
        });
    }
    public onPageChanged(event){
        this.page = event;
        this.getAllOrders("",this.page);
        window.scrollTo(0,0);
    }
}
