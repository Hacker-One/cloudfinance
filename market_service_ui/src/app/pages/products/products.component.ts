import {Component, OnInit, ViewChild, HostListener, Renderer2, ElementRef} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {MatDialog, MatSnackBar} from '@angular/material';
import { ProductDialogComponent } from '../../shared/products-carousel/product-dialog/product-dialog.component';
import { AppService } from '../../app.service';
import { Product, Category } from "../../app.models";
import {HttpInterceptorService} from '../../theme/utils/httpInterceptor.service';
import {LocalStorage} from '../../local.storage';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  @ViewChild('sidenav') sidenav: any;
  @ViewChild('util') util: ElementRef;
  public sidenavOpen:boolean = true;
  private sub: any;
  public viewType: string = 'grid';
  public viewCol: number = 20;
  public count:any;
  public queryTypes = [
      {"name":"综合查询","value":""},
      {"name":"商品名称","value":"name"},
      {"name":"供应商名称","value":"vendorName"},
      {name:"Type",value:"typeName"},
      {name:"Category",value:"categoryName"},
  ];
  public type:any="";
  public types=[];
  public category:any="";
  public categorys=[];
  public querySelect:any="请选择";
  public querySelects=[];
  public queryType:any;
  public total=0;
  public sortings = [
      {name:"综合排序",value:""},
      {name:"商品名称",value:"name"},
      {name:"Type",value:"typeName"},
      {name:"Category",value:"categoryName"},
      {name:"Vendor",value:"vendorName"},
  ];
  public sort:any;
  public postBody={
      "start":1,
      "name":"",
      "typeName":"",
      "categoryName":"",
      "vendorName":""
  }
  public products: Array<any> = [];
  public categories:Category[];
  public brands = [];
  public colors = ["#5C6BC0","#66BB6A","#EF5350","#BA68C8","#FF4081","#9575CD","#90CAF9","#B2DFDB","#DCE775","#FFD740","#00E676","#FBC02D","#FF7043","#F5F5F5","#000000"];
  public sizes = ["S","M","L","XL","2XL","32","36","38","46","52","13.3\"","15.4\"","17\"","21\"","23.4\""];
  public page:any;
  public qu:any="";
  constructor(public localStorage:LocalStorage,private renderer: Renderer2,public snackBar: MatSnackBar,public httpUtil:HttpInterceptorService,private activatedRoute: ActivatedRoute, public appService:AppService, public dialog: MatDialog, private router: Router) { }

  ngOnInit() {
      this.renderer.listen("window","scroll",event=>{
          const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
          if(scrollTop>345){
              this.renderer.addClass(this.util.nativeElement,"sfixed");
          }else{
              this.renderer.removeClass(this.util.nativeElement,"sfixed");
          }
      })
      // window.onscroll = function(e){
      //     var scrolltop=document.documentElement.scrollTop||document.body.scrollTop;
      //     console.log(that.util.contentTop)
      //     console.log(scrolltop);
      // }
    this.count =15;

    this.sort = this.sortings[0];
    this.queryType=this.queryTypes[0];
    this.sub = this.activatedRoute.params.subscribe(params => {
      //console.log(params['name']);
    });
    if(window.innerWidth < 960){
      this.sidenavOpen = false;
    };
    if(window.innerWidth < 1280){
      this.viewCol = 33.3;
    };
    this.getTypes();
    this.getCategorys();
    this.getBrands();
    this.getAllProducts();
  }

  public getAllProducts(){
      this.httpUtil.post('/v1/products/list',this.postBody,data=>{
          if(data.statusCode=="200"){
            if(data.code=="000"){
                    this.total=data.data['size:']
                    this.products=data.data['products:'];
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

  public getCategories(){
    if(this.appService.Data.categories.length == 0) {
      this.appService.getCategories().subscribe(data => {
        this.categories = data;
        this.appService.Data.categories = data;
      });
    }
    else{
      this.categories = this.appService.Data.categories;
    }
  }

  public getBrands(){
    this.brands = this.appService.getBrands();
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  @HostListener('window:resize')
  public onWindowResize():void {
    (window.innerWidth < 960) ? this.sidenavOpen = false : this.sidenavOpen = true;
    (window.innerWidth < 1280) ? this.viewCol = 33.3 : this.viewCol = 25;
  }

  public changeQuery(query){
    this.queryType = query;
     if(query.value=="typeName"){
         this.querySelect=this.type;
         this.querySelects=this.types;
         this.querySelects.unshift({"name":"请选择","value":""})
     }
      if(query.value=="categoryName"){
          this.querySelect=this.category;
          this.querySelects=this.categorys;
          this.querySelects.unshift({"name":"请选择","value":""})
      }
      if(query.value==""){
          this.postBody.typeName="";
          this.postBody.name="";
          this.postBody.vendorName="";
          this.postBody.categoryName="";
          this.postBody.start=1;
          this.page=1;
          this.getAllProducts();
      }
  }

  public changeSorting(sort){
    this.sort = sort;
    if(sort.value==""){
        delete this.postBody['asc'];
    }else{
        this.postBody['asc']=sort.value;
    }
    this.page=1;
    this.postBody.start=1;
    this.getAllProducts()
  }

  public changeViewType(viewType, viewCol){
    this.viewType = viewType;
    this.viewCol = viewCol;
  }

  public openProductDialog(product){
      if(this.localStorage.get("userName")){
          let dialogRef = this.dialog.open(ProductDialogComponent, {
              data: product,
              panelClass: 'product-dialog'
          });
          dialogRef.afterClosed().subscribe(product => {
              if(product){
                  this.router.navigate(['/products', product.id, product.name]);
              }
          });
      }else{
          this.router.navigate(['/sign-in']);
      }

  }

  public onPageChanged(event){
      this.page = event;
      this.postBody.start=this.page;
      this.getAllProducts();
      window.scrollTo(0,0);
  }
  public getTypes(){
      this.httpUtil.get('/v1/types',data=>{
             this.type={"name":"请选择","value":""};
             this.types=data;
      },err=>{
          this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
      });
  }
  public getCategorys(){
      this.httpUtil.get('/v1/categorys',data=>{
              this.category={"name":"请选择","value":""};
              this.categorys=data;
      },err=>{
          this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
      });
  }
  public onChangeCategory(event){
    if(event.target){
      this.router.navigate(['/products', event.target.innerText.toLowerCase()]);
    }
  }
  public query(event){
      this.postBody.typeName="";
      this.postBody.name="";
      this.postBody.vendorName="";
      this.postBody.categoryName="";
      if(this.queryType.value=='name'){
          this.postBody.name=this.qu;
      }
      if(this.queryType.value=='vendorName'){
          this.postBody.vendorName=this.qu;
      }
      if(this.queryType.value=='categoryName'){
          this.postBody.categoryName=event.name!="请选择"?event.name:'';
          this.querySelect=event;
      }
      if(this.queryType.value=='typeName'){
          this.postBody.typeName=event.name!="请选择"?event.name:'';
          this.querySelect=event;
      }
      this.page=1;
      this.postBody.start=1;
      this.getAllProducts();
  }

}
