import { Component, ViewEncapsulation, OnInit, Inject} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA, MatSnackBar, MatDialog} from '@angular/material';
import { SwiperConfigInterface } from 'ngx-swiper-wrapper';
import {  AppService } from '../../../app.service';
import { Product } from '../../../app.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {GoOrderDialogComponent} from '../goOrder-dialog/goOrder-dialog.component';
import {HttpInterceptorService} from '../../../theme/utils/httpInterceptor.service';

@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styleUrls: ['./product-dialog.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ProductDialogComponent implements OnInit {
  public config: SwiperConfigInterface = {}
  infoForm: FormGroup;
  constructor(public formBuilder: FormBuilder,public httpUtil:HttpInterceptorService,public dialog: MatDialog, private router: Router, public snackBar: MatSnackBar,public appService:AppService,
            public dialogRef: MatDialogRef<ProductDialogComponent>,
            @Inject(MAT_DIALOG_DATA) public product: Product) { }

  ngOnInit() {
      this.infoForm = this.formBuilder.group({
          'purchaseCode': ['', Validators.compose([Validators.required])]
      });

  }
    public onInfoFormSubmit(values:Object):void {
        if (this.infoForm.valid) {
           const product={
            product:this.product.id,
            productName:this.product.name,
            account:'10001',
            pcode:this.infoForm.value.purchaseCode
           }
            this.httpUtil.post('/v1/orders',product,data=>{
                if(data.statusCode=="200"){
                    if(data.code=="000"){
                            this.close();
                            let dialogRef = this.dialog.open(GoOrderDialogComponent, {
                                data: product,
                                panelClass: 'product-dialog'
                            });
                            dialogRef.afterClosed().subscribe(product => {
                                if(product){
                                    this.router.navigate(['/products', product.id, product.name]);
                                }
                            });
                            this.snackBar.open('购买成功!', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });

                    }else{
                        this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                    }
                }
            },err=>{
                this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
            });

        }
    }
  ngAfterViewInit(){
    this.config = {
      slidesPerView: 1,
      spaceBetween: 0,
      keyboard: true,
      navigation: true,
      pagination: false,
      grabCursor: true,
      loop: false,
      preloadImages: false,
      lazy: true,
      effect: "fade",
      fadeEffect: {
        crossFade: true
      }
    }
  }

  public close(): void {
    this.dialogRef.close();
  }
}
