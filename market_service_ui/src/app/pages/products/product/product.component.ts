import { Component, OnInit, ViewChild } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import {MatDialog, MatSnackBar} from '@angular/material';
import { SwiperConfigInterface, SwiperDirective } from 'ngx-swiper-wrapper';
import { Data } from '../../../app.service';
import { Product } from "../../../app.models";
import { emailValidator } from '../../../theme/utils/app-validators';
import { ProductZoomComponent } from './product-zoom/product-zoom.component';
import {HttpInterceptorService} from '../../../theme/utils/httpInterceptor.service';
import {ProductDialogComponent} from '../../../shared/products-carousel/product-dialog/product-dialog.component';
import {LocalStorage} from '../../../local.storage';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  @ViewChild('zoomViewer') zoomViewer;
  @ViewChild(SwiperDirective) directiveRef: SwiperDirective;
  public config: SwiperConfigInterface={};
  public product:Product;
  public image: any;
  public images=[];
  public zoomImage: any;
  private sub: any;
  public vendorINfo={
    name:"",
    email:"",
    phone:"",
    url:"",

  }
  public form: FormGroup;

  constructor(  public router:Router,public localStorage:LocalStorage,public snackBar: MatSnackBar,public httpUtil:HttpInterceptorService,private activatedRoute: ActivatedRoute, public dialog: MatDialog, public formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.sub = this.activatedRoute.params.subscribe(params => {
      this.getProductById(params['id']);
    });
    this.form = this.formBuilder.group({
      'review': [null, Validators.required],
      'name': [null, Validators.compose([Validators.required, Validators.minLength(4)])],
      'email': [null, Validators.compose([Validators.required, emailValidator])]
    });

  }

  ngAfterViewInit(){
    this.config = {
      observer: false,
      slidesPerView: 4,
      spaceBetween: 10,
      keyboard: true,
      navigation: true,
      pagination: false,
      loop: false,
      preloadImages: false,
      lazy: true,
      breakpoints: {
        480: {
          slidesPerView: 2
        },
        600: {
          slidesPerView: 3,
        }
      }
    }
  }

  public getProductById(id){
      this.httpUtil.get('/v1/products/'+id,data=>{
          if(data.statusCode=="200"){
              if(data.code=="000"){
                  this.product=data.data;
                  if(!this.product.tags){
                      this.product.tags=new Array();
                  }
                  if(!this.product.pictures){
                      this.product.pictures=new Array();
                  }
                  this.images= this.product.pictures;
                  if(this.product.pictures.length>0){
                      this.image=this.product.pictures[0].url;
                      this.zoomImage=this.product.pictures[0].url;
                  }
                  this.httpUtil.get('/v1/vendors/'+this.product.vendor,data=>{
                      if(data.statusCode=="200"){
                          if(data.code=="000"){
                              this.vendorINfo.name=data.data.name;
                              this.vendorINfo.email=data.data.email;
                              this.vendorINfo.phone=data.data.contact;
                              this.vendorINfo.url=data.data.url;
                          }else{
                              this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                          }
                      }else{
                          this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                      }
                  },err=>{
                      this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                  });
                    setTimeout(() => {
                      this.config.observer = true;
                     // this.directiveRef.setIndex(0);
                    });
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
  public open(){
      window.open(this.product.descUrl, '_blank')
  }
  public getRelatedProducts(){
    // this.appService.getProducts("",0).subscribe(data => {
    //   this.relatedProducts = data;
    // })
  }

  public selectImage(image){
    this.image = image;
    this.zoomImage = image;
  }

  public onMouseMove(e){
    if(window.innerWidth >= 1280){
      var image, offsetX, offsetY, x, y, zoomer;
      image = e.currentTarget;
      offsetX = e.offsetX;
      offsetY = e.offsetY;
      x = offsetX/image.offsetWidth*100;
      y = offsetY/image.offsetHeight*100;
      zoomer = this.zoomViewer.nativeElement.children[0];
      if(zoomer){
        zoomer.style.backgroundPosition = x + '% ' + y + '%';
        zoomer.style.display = "block";
        zoomer.style.height = image.height + 'px';
        zoomer.style.width = image.width + 'px';
      }
    }
  }

  public onMouseLeave(event){
    this.zoomViewer.nativeElement.children[0].style.display = "none";
  }

  public openZoomViewer(){
    this.dialog.open(ProductZoomComponent, {
      data: this.zoomImage,
      panelClass: 'zoom-dialog'
    });
  }
    public openProductDialog(){
        if(this.localStorage.get("userName")){
            let dialogRef = this.dialog.open(ProductDialogComponent, {
                data: this.product,
                panelClass: 'product-dialog'
            });
            dialogRef.afterClosed().subscribe(product => {

            });
        }else{
            this.router.navigate(['/sign-in']);
        }

    }
  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  public onSubmit(values:Object):void {
    if (this.form.valid) {
      //email sent
    }
  }

}
