(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["app-pages-home-home-module"],{

/***/ "./src/app/pages/home/home.component.html":
/*!************************************************!*\
  !*** ./src/app/pages/home/home.component.html ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-main-carousel [slides]=\"slides\"></app-main-carousel>\r\n\r\n<!--<div fxLayout=\"row wrap\" class=\"info-bar\">-->\r\n    <!--<div fxFlex=\"100\" fxFlex.gt-md=\"25\" fxFlex.gt-xs=\"50\">-->\r\n        <!--<mat-card class=\"light-block\" fxLayout=\"row\"  fxLayoutAlign=\"start center\">-->\r\n            <!--<mat-icon class=\"mat-icon-xlg text-muted m-0\">card_giftcard</mat-icon>-->\r\n            <!--<div class=\"content\">-->\r\n                <!--<p>BONUS PLUS</p> -->\r\n                <!--<span class=\"text-muted m-0\">Make fun of shopping and collect bonuses</span>-->\r\n            <!--</div> -->\r\n        <!--</mat-card> -->\r\n    <!--</div>-->\r\n    <!--<div fxFlex=\"100\" fxFlex.gt-md=\"25\" fxFlex.gt-xs=\"50\"> -->\r\n        <!--<mat-card class=\"light-block\" fxLayout=\"row\"  fxLayoutAlign=\"start center\">-->\r\n            <!--<mat-icon class=\"mat-icon-xlg text-muted m-0\">local_shipping</mat-icon>-->\r\n            <!--<div class=\"content\">-->\r\n                <!--<p>FREE SHIPPING</p> -->\r\n                <!--<span class=\"text-muted m-0\">Free shipping on all orders over $99</span>-->\r\n            <!--</div> -->\r\n        <!--</mat-card>-->\r\n    <!--</div>-->\r\n    <!--<div fxFlex=\"100\" fxFlex.gt-md=\"25\" fxFlex.gt-xs=\"50\"> -->\r\n        <!--<mat-card class=\"light-block\" fxLayout=\"row\"  fxLayoutAlign=\"start center\">-->\r\n            <!--<mat-icon class=\"mat-icon-xlg text-muted m-0\">monetization_on</mat-icon>-->\r\n            <!--<div class=\"content\">-->\r\n                <!--<p>MONEY BACK GUARANTEE</p> -->\r\n                <!--<span class=\"text-muted m-0\">30 Days money return guarantee</span>-->\r\n            <!--</div> -->\r\n        <!--</mat-card>-->\r\n    <!--</div>-->\r\n    <!--<div fxFlex=\"100\" fxFlex.gt-md=\"25\" fxFlex.gt-xs=\"50\"> -->\r\n        <!--<mat-card class=\"light-block\" fxLayout=\"row\"  fxLayoutAlign=\"start center\">-->\r\n            <!--<mat-icon class=\"mat-icon-xlg text-muted m-0\">history</mat-icon>-->\r\n            <!--<div class=\"content\">-->\r\n                <!--<p>ONLINE SUPPORT 24/7</p> -->\r\n                <!--<span class=\"text-muted m-0\">Call us: (+100) 123 456 7890</span>-->\r\n            <!--</div> -->\r\n        <!--</mat-card>-->\r\n    <!--</div>-->\r\n<!--</div>-->\r\n\r\n<!--<app-banners [banners]=\"banners\"></app-banners>-->\r\n\r\n<div class=\"products-tabs\">\r\n    <!--<mat-tab-group mat-stretch-tabs (selectedTabChange)=\"onLinkClick($event)\">-->\r\n\r\n        <!--<mat-tab label=\"Featured\">-->\r\n           <!--<app-products-carousel [products]=\"featuredProducts\"></app-products-carousel>-->\r\n        <!--</mat-tab>-->\r\n        <!--<mat-tab label=\"On Sale\">-->\r\n           <!--<app-products-carousel [products]=\"onSaleProducts\"></app-products-carousel>-->\r\n        <!--</mat-tab>-->\r\n        <!--<mat-tab label=\"Top Rated\">-->\r\n            <!--<app-products-carousel [products]=\"topRatedProducts\"></app-products-carousel>-->\r\n        <!--</mat-tab>-->\r\n        <!--<mat-tab label=\"New Arrivals\">-->\r\n            <!--<app-products-carousel [products]=\"newArrivalsProducts\"></app-products-carousel>-->\r\n        <!--</mat-tab>-->\r\n    <!--</mat-tab-group>-->\r\n    <router-outlet></router-outlet>\r\n\r\n</div>\r\n\r\n<!--<app-brands-carousel [brands]=\"brands\"></app-brands-carousel>-->\r\n"

/***/ }),

/***/ "./src/app/pages/home/home.component.scss":
/*!************************************************!*\
  !*** ./src/app/pages/home/home.component.scss ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".products-tabs {\n  margin-top: 30px; }\n"

/***/ }),

/***/ "./src/app/pages/home/home.component.ts":
/*!**********************************************!*\
  !*** ./src/app/pages/home/home.component.ts ***!
  \**********************************************/
/*! exports provided: HomeComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeComponent", function() { return HomeComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _app_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../app.service */ "./src/app/app.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var HomeComponent = /** @class */ (function () {
    function HomeComponent(appService) {
        this.appService = appService;
        this.slides = [
            { title: 'The biggest sale', subtitle: 'Special for today', image: 'assets/images/carousel/banner1.jpg' },
            { title: 'Summer collection', subtitle: 'New Arrivals On Sale', image: 'assets/images/carousel/banner2.jpg' },
            { title: 'The biggest sale', subtitle: 'Special for today', image: 'assets/images/carousel/banner3.jpg' },
            { title: 'Summer collection', subtitle: 'New Arrivals On Sale', image: 'assets/images/carousel/banner4.jpg' },
            { title: 'The biggest sale', subtitle: 'Special for today', image: 'assets/images/carousel/banner5.jpg' }
        ];
        this.brands = [];
        this.banners = [];
    }
    HomeComponent.prototype.ngOnInit = function () {
        this.getBanners();
        //this.getProducts();
        //this.getBrands();
    };
    HomeComponent.prototype.getBanners = function () {
        var _this = this;
        this.appService.getBanners().subscribe(function (data) {
            _this.banners = data;
        });
    };
    HomeComponent.prototype.getBrands = function () {
        this.brands = this.appService.getBrands();
    };
    HomeComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-home',
            template: __webpack_require__(/*! ./home.component.html */ "./src/app/pages/home/home.component.html"),
            styles: [__webpack_require__(/*! ./home.component.scss */ "./src/app/pages/home/home.component.scss")]
        }),
        __metadata("design:paramtypes", [_app_service__WEBPACK_IMPORTED_MODULE_1__["AppService"]])
    ], HomeComponent);
    return HomeComponent;
}());



/***/ }),

/***/ "./src/app/pages/home/home.module.ts":
/*!*******************************************!*\
  !*** ./src/app/pages/home/home.module.ts ***!
  \*******************************************/
/*! exports provided: routes, HomeModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "routes", function() { return routes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeModule", function() { return HomeModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _home_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./home.component */ "./src/app/pages/home/home.component.ts");
/* harmony import */ var _products_product_product_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../products/product/product.component */ "./src/app/pages/products/product/product.component.ts");
/* harmony import */ var _products_products_module__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../products/products.module */ "./src/app/pages/products/products.module.ts");
/* harmony import */ var _products_products_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../products/products.component */ "./src/app/pages/products/products.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};








var routes = [
    { path: '', component: _home_component__WEBPACK_IMPORTED_MODULE_4__["HomeComponent"], pathMatch: 'full', children: [
            { path: '', component: _products_products_component__WEBPACK_IMPORTED_MODULE_7__["ProductsComponent"], data: { breadcrumb: 'All Products' } },
            { path: ':id/:name', component: _products_product_product_component__WEBPACK_IMPORTED_MODULE_5__["ProductComponent"], data: { breadcrumb: '商品详情' } }
        ]
    }
];
var HomeModule = /** @class */ (function () {
    function HomeModule() {
    }
    HomeModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _products_products_module__WEBPACK_IMPORTED_MODULE_6__["ProductsModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes),
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_3__["SharedModule"]
            ],
            declarations: [
                _home_component__WEBPACK_IMPORTED_MODULE_4__["HomeComponent"]
            ]
        })
    ], HomeModule);
    return HomeModule;
}());



/***/ }),

/***/ "./src/app/pages/products/product/product-zoom/product-zoom.component.html":
/*!*********************************************************************************!*\
  !*** ./src/app/pages/products/product/product-zoom/product-zoom.component.html ***!
  \*********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"controls\">\r\n    <button mat-mini-fab color=\"primary\" class=\"zoom-in\" (click)=\"zoomIn()\"><mat-icon>zoom_in</mat-icon></button>\r\n    <button mat-mini-fab color=\"primary\" class=\"zoom-out\" (click)=\"zoomOut()\"><mat-icon>zoom_out</mat-icon></button>\r\n    <button mat-mini-fab color=\"warn\" class=\"close\" (click)=\"close()\"><mat-icon>close</mat-icon></button>\r\n</div>\r\n<div mat-dialog-content>\r\n    <div class=\"viewer\">\r\n        <img [src]=\"image\" #zoomImage>\r\n    </div>    \r\n</div>"

/***/ }),

/***/ "./src/app/pages/products/product/product-zoom/product-zoom.component.scss":
/*!*********************************************************************************!*\
  !*** ./src/app/pages/products/product/product-zoom/product-zoom.component.scss ***!
  \*********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".zoom-dialog .mat-dialog-container {\n  overflow: visible !important; }\n  .zoom-dialog .mat-dialog-container .controls {\n    position: relative; }\n  .zoom-dialog .mat-dialog-container .controls button {\n      position: absolute;\n      top: -44px; }\n  .zoom-dialog .mat-dialog-container .controls .zoom-in {\n      right: 44px; }\n  .zoom-dialog .mat-dialog-container .controls .zoom-out {\n      right: 0; }\n  .zoom-dialog .mat-dialog-container .controls .close {\n      right: -44px; }\n  .zoom-dialog .mat-dialog-container .viewer {\n    width: 100%;\n    text-align: center; }\n  .zoom-dialog .mat-dialog-container .viewer img {\n      max-width: 60%; }\n"

/***/ }),

/***/ "./src/app/pages/products/product/product-zoom/product-zoom.component.ts":
/*!*******************************************************************************!*\
  !*** ./src/app/pages/products/product/product-zoom/product-zoom.component.ts ***!
  \*******************************************************************************/
/*! exports provided: ProductZoomComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductZoomComponent", function() { return ProductZoomComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (undefined && undefined.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};


var ProductZoomComponent = /** @class */ (function () {
    function ProductZoomComponent(dialogRef, image) {
        this.dialogRef = dialogRef;
        this.image = image;
        this.count = 10;
        this.maxWidth = 60;
    }
    ProductZoomComponent.prototype.ngOnInit = function () { };
    ProductZoomComponent.prototype.close = function () {
        this.dialogRef.close();
    };
    ProductZoomComponent.prototype.zoomIn = function () {
        if (this.count < 60) {
            this.maxWidth = this.maxWidth + this.count;
            this.zoomImage.nativeElement.style.maxWidth = this.maxWidth + '%';
            this.count = this.count + 10;
        }
    };
    ProductZoomComponent.prototype.zoomOut = function () {
        if (this.count > 10) {
            this.count = this.count - 10;
            this.maxWidth = this.maxWidth - this.count;
            this.zoomImage.nativeElement.style.maxWidth = this.maxWidth + '%';
        }
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])('zoomImage'),
        __metadata("design:type", Object)
    ], ProductZoomComponent.prototype, "zoomImage", void 0);
    ProductZoomComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-product-zoom',
            template: __webpack_require__(/*! ./product-zoom.component.html */ "./src/app/pages/products/product/product-zoom/product-zoom.component.html"),
            styles: [__webpack_require__(/*! ./product-zoom.component.scss */ "./src/app/pages/products/product/product-zoom/product-zoom.component.scss")],
            encapsulation: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewEncapsulation"].None
        }),
        __param(1, Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Inject"])(_angular_material__WEBPACK_IMPORTED_MODULE_1__["MAT_DIALOG_DATA"])),
        __metadata("design:paramtypes", [_angular_material__WEBPACK_IMPORTED_MODULE_1__["MatDialogRef"], Object])
    ], ProductZoomComponent);
    return ProductZoomComponent;
}());



/***/ }),

/***/ "./src/app/pages/products/product/product.component.html":
/*!***************************************************************!*\
  !*** ./src/app/pages/products/product/product.component.html ***!
  \***************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div fxLayout=\"row wrap\">\r\n    <div fxFlex=\"100\" fxFlex.gt-md=\"35\" fxFlex.md=\"45\">\r\n\r\n        <mat-card class=\"product-image\" *ngIf=\"image\">\r\n            <button mat-icon-button (click)=\"openZoomViewer()\" fxHide=\"false\" fxHide.gt-md><mat-icon>fullscreen</mat-icon></button>\r\n            <img *ngIf=\"image\" [src]=\"image\" (mousemove)=\"onMouseMove($event)\" (mouseleave)=\"onMouseLeave($event)\"/>\r\n        </mat-card>\r\n\r\n        <div class=\"small-carousel\">\r\n            <div class=\"swiper-container\" [swiper]=\"config\">\r\n                <div class=\"swiper-wrapper\">\r\n                    <div *ngFor=\"let image of images\" class=\"swiper-slide\">\r\n                        <mat-card (click)=\"selectImage(image.url)\" class=\"p-1\">\r\n                            <img [attr.data-src]=\"image.url\" class=\"swiper-lazy\"/>\r\n                            <div class=\"swiper-lazy-preloader\"></div>\r\n                        </mat-card>\r\n                    </div>\r\n                </div>\r\n                <button mat-icon-button class=\"swiper-button-prev swipe-arrow\"><mat-icon>keyboard_arrow_left</mat-icon></button>\r\n                <button mat-icon-button class=\"swiper-button-next swipe-arrow\"><mat-icon>keyboard_arrow_right</mat-icon></button>\r\n            </div>\r\n        </div>\r\n\r\n\r\n    </div>\r\n    <div fxFlex=\"100\" fxFlex.gt-md=\"65\" fxFlex.md=\"55\" ngClass.gt-sm=\"px-3 m-0\" ngClass.sm=\"mt-2\" ngClass.xs=\"mt-2\">\r\n\r\n        <div #zoomViewer fxShow=\"false\" fxShow.gt-md>\r\n            <mat-card *ngIf=\"zoomImage\" class=\"zoom-viewer mat-elevation-z18\" [ngStyle]=\"{'background-image': 'url(' + zoomImage + ')'}\"></mat-card>\r\n        </div>\r\n\r\n        <h2>{{product?.name}}</h2>\r\n        <div class=\"py-1 lh\">\r\n            <p><span class=\"text-muted fw-500\">Type: </span><span>{{product?.typeName}}</span></p>\r\n        </div>\r\n        <div class=\"py-1 lh\">\r\n            <p><span class=\"text-muted fw-500\">Category: </span><span>{{product?.categoryName}}</span></p>\r\n        </div>\r\n        <div fxLayout=\"row\" fxLayout.xs=\"column\" fxLayoutAlign=\"space-between center\" class=\"details text-muted py-1\">\r\n            <div class=\"color\">\r\n                <span class=\"fw-500\">产品地址:</span>&nbsp;&nbsp;&nbsp;<a href=\"avascript:void(0)\" (click)=\"open()\">{{product?.descUrl}} </a>\r\n            </div>\r\n        </div>\r\n        <!--<div class=\"py-1\">-->\r\n            <!--<app-rating [ratingsCount]=\"product?.ratingsCount\" [ratingsValue]=\"product?.ratingsValue\" [direction]=\"'row'\"></app-rating>-->\r\n        <!--</div>-->\r\n        <p class=\"py-1 text-muted lh\">{{product?.shortDesc}}</p>\r\n\r\n        <div class=\"divider mt-1\" *ngIf=\"product?.tags&&product?.tags.length>0\"></div>\r\n        <app-tags [tags]=\"product?.tags\"></app-tags>\r\n        <div class=\"divider mt-1\"></div>\r\n        <!--<h2 class=\"py-2 new-price\">${{product?.newPrice}}</h2>-->\r\n\r\n        <!--<div fxLayout=\"row\" fxLayout.xs=\"column\" fxLayoutAlign=\"space-between center\" class=\"details text-muted py-1\">-->\r\n            <!--<div *ngIf=\"product?.color\" class=\"color\">-->\r\n                <!--<span class=\"fw-500\">Select Color:</span>-->\r\n                <!--<button mat-raised-button *ngFor=\"let color of product?.color\" [style.background]=\"color\">&nbsp;</button>-->\r\n            <!--</div>-->\r\n            <!--<div *ngIf=\"product?.size\" class=\"size\" ngClass.xs=\"mt-1\">-->\r\n                <!--<span class=\"fw-500\">Select Size:</span>-->\r\n                <!--<button mat-raised-button *ngFor=\"let size of product?.size\">{{size}}</button>-->\r\n            <!--</div>-->\r\n        <!--</div>-->\r\n\r\n        <div class=\"py-1\">\r\n            <div fxLayout=\"row\" fxLayout.xs=\"column\" [fxLayoutAlign]=\"'start'\" class=\"text-muted\">\r\n                    <button  color=\"primary\" class=\"mat-raised-button mat-primary\"  (click)=\"openProductDialog()\">\r\n                        <span class=\"mat-button-wrapper\">GET</span>\r\n                        <div class=\"mat-button-ripple mat-ripple\"></div>\r\n                        <div class=\"mat-button-focus-overlay\"></div>\r\n                    </button>\r\n            </div>\r\n            <!--<app-controls [product]=\"product\" [type]=\"'all'\" (onOpenProductDialog)=\"openProductDialog()\"></app-controls>-->\r\n        </div>\r\n\r\n        <div class=\"divider\"></div>\r\n\r\n        <!--<div fxLayout=\"row\" fxLayoutAlign=\"space-between center\" class=\"text-muted py-1\">-->\r\n            <!--<button mat-button><mat-icon>mail_outline</mat-icon> Email to a Friend</button>-->\r\n            <!--<button mat-icon-button><mat-icon>share</mat-icon></button>-->\r\n        <!--</div>-->\r\n\r\n    </div>\r\n    <!--<div fxFlex=\"100\" fxFlex.gt-md=\"20\" fxHide fxShow.gt-md> -->\r\n\r\n        <!--<div fxLayout=\"row wrap\" class=\"info-bar\" ngClass.gt-md=\"m-0\">-->\r\n            <!--<div fxFlex=\"100\" fxFlex.md=\"25\" fxFlex.sm=\"50\">-->\r\n                <!--<mat-card class=\"light-block\" fxLayout=\"row\"  fxLayoutAlign=\"start center\">-->\r\n                    <!--<mat-icon class=\"mat-icon-xlg text-muted m-0\">card_giftcard</mat-icon>-->\r\n                    <!--<div class=\"content\">-->\r\n                        <!--<p>BONUS PLUS</p> -->\r\n                        <!--<span class=\"text-muted m-0\">Make fun of shopping and collect bonuses</span>-->\r\n                    <!--</div> -->\r\n                <!--</mat-card> -->\r\n            <!--</div>-->\r\n            <!--<div fxFlex=\"100\" fxFlex.md=\"25\" fxFlex.sm=\"50\" class=\"mt-16\"> -->\r\n                <!--<mat-card class=\"light-block\" fxLayout=\"row\"  fxLayoutAlign=\"start center\">-->\r\n                    <!--<mat-icon class=\"mat-icon-xlg text-muted m-0\">local_shipping</mat-icon>-->\r\n                    <!--<div class=\"content\">-->\r\n                        <!--<p>FREE SHIPPING</p> -->\r\n                        <!--<span class=\"text-muted m-0\">Free shipping on all orders over $99</span>-->\r\n                    <!--</div> -->\r\n                <!--</mat-card>-->\r\n            <!--</div>-->\r\n            <!--<div fxFlex=\"100\" fxFlex.md=\"25\"fxFlex.sm=\"50\" class=\"mt-16\"> -->\r\n                <!--<mat-card class=\"light-block\" fxLayout=\"row\"  fxLayoutAlign=\"start center\">-->\r\n                    <!--<mat-icon class=\"mat-icon-xlg text-muted m-0\">monetization_on</mat-icon>-->\r\n                    <!--<div class=\"content\">-->\r\n                        <!--<p>MONEY BACK GUARANTEE</p> -->\r\n                        <!--<span class=\"text-muted m-0\">30 Days money return guarantee</span>-->\r\n                    <!--</div> -->\r\n                <!--</mat-card>-->\r\n            <!--</div>-->\r\n            <!--<div fxFlex=\"100\" fxFlex.md=\"25\" fxFlex.sm=\"50\" class=\"mt-16\"> -->\r\n                <!--<mat-card class=\"light-block\" fxLayout=\"row\"  fxLayoutAlign=\"start center\">-->\r\n                    <!--<mat-icon class=\"mat-icon-xlg text-muted m-0\">history</mat-icon>-->\r\n                    <!--<div class=\"content\">-->\r\n                        <!--<p>ONLINE SUPPORT 24/7</p> -->\r\n                        <!--<span class=\"text-muted m-0\">Call us: (+100) 123 456 7890</span>-->\r\n                    <!--</div> -->\r\n                <!--</mat-card>-->\r\n            <!--</div>-->\r\n        <!--</div>-->\r\n\r\n    <!--</div>-->\r\n</div>\r\n\r\n<div fxLayout=\"row wrap\" class=\"mt-2\">\r\n    <div fxFlex=\"100\" fxFlex.gt-md=\"100\" fxFlex.md=\"100\">\r\n        <mat-card>\r\n            <mat-tab-group [@.disabled]=\"true\" [selectedIndex]=\"0\">\r\n                <mat-tab label=\"描述\">\r\n                    <div class=\"full-desc lh\">\r\n                        <p>{{product?.longDesc}}</p>\r\n                        <img *ngIf=\"product?.picture\" [src]=\"product?.picture\" style=\"width: 100%;height: 100%\"/>\r\n                    </div>\r\n                </mat-tab>\r\n                <mat-tab label=\"供应商信息\">\r\n                    <div fxLayout=\"row\" fxLayout.xs=\"column\" fxLayoutAlign=\"space-between center\" class=\"details text-muted py-1\">\r\n                        <div class=\"color\">\r\n                            <span class=\"fw-500\">供应商名称:</span>&nbsp;&nbsp;&nbsp;{{vendorINfo?.name}}\r\n                        </div>\r\n                    </div>\r\n                    <div fxLayout=\"row\" fxLayout.xs=\"column\" fxLayoutAlign=\"space-between center\" class=\"details text-muted py-1\">\r\n                        <div class=\"color\">\r\n                            <span class=\"fw-500\">电话:</span>&nbsp;&nbsp;&nbsp;{{vendorINfo?.phone}}\r\n                        </div>\r\n                    </div>\r\n                    <div fxLayout=\"row\" fxLayout.xs=\"column\" fxLayoutAlign=\"space-between center\" class=\"details text-muted py-1\">\r\n                        <div class=\"color\">\r\n                            <span class=\"fw-500\">邮箱:</span>&nbsp;&nbsp;&nbsp;{{vendorINfo?.email}}\r\n                        </div>\r\n                    </div>\r\n                    <div fxLayout=\"row\" fxLayout.xs=\"column\" fxLayoutAlign=\"space-between center\" class=\"details text-muted py-1\">\r\n                        <div class=\"color\">\r\n                            <span class=\"fw-500\">网站:</span>&nbsp;&nbsp;&nbsp;{{vendorINfo?.url}}\r\n                        </div>\r\n                    </div>\r\n                </mat-tab>\r\n            </mat-tab-group>\r\n        </mat-card>\r\n    </div>\r\n    <!--<div fxFlex=\"100\" fxFlex.gt-md=\"22\" fxFlex.md=\"26\" ngClass.gt-sm=\"pl-3\" fxHide fxShow.gt-sm>-->\r\n        <!--<img src=\"https://via.placeholder.com/400x320/EEEEEE/9E9E9E/?text=Banner%201\" alt=\"\" class=\"mw-100 d-block\">-->\r\n        <!--<img src=\"https://via.placeholder.com/400x320/EEEEEE/9E9E9E/?text=Banner%202\" alt=\"\" class=\"mw-100 d-block mt-3\">-->\r\n        <!--<img src=\"https://via.placeholder.com/400x320/EEEEEE/9E9E9E/?text=Banner%203\" alt=\"\" class=\"mw-100 d-block mt-3\">-->\r\n    <!--</div>-->\r\n</div>\r\n\r\n<!--<div class=\"py-2 mt-2\">-->\r\n    <!--<h2>Related Products</h2>-->\r\n    <!--<div class=\"divider\"></div>-->\r\n    <!--<app-products-carousel [products]=\"relatedProducts\"></app-products-carousel>-->\r\n<!--</div>-->\r\n\r\n\r\n"

/***/ }),

/***/ "./src/app/pages/products/product/product.component.scss":
/*!***************************************************************!*\
  !*** ./src/app/pages/products/product/product.component.scss ***!
  \***************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".mat-card.product-image {\n  padding: 6px;\n  text-align: center; }\n  .mat-card.product-image button {\n    position: absolute;\n    top: 0;\n    right: 0;\n    z-index: 99; }\n  .mat-card.product-image img {\n    max-width: 100%; }\n  .small-carousel {\n  margin-top: 8px; }\n  .small-carousel .swiper-container {\n    padding: 2px; }\n  .small-carousel .swiper-container .swiper-slide {\n      text-align: center; }\n  .small-carousel .swiper-container .swiper-slide img {\n        max-width: 100%; }\n  .small-carousel .swiper-button-next.swiper-button-disabled,\n  .small-carousel .swiper-button-prev.swiper-button-disabled {\n    opacity: 0; }\n  .small-carousel .swiper-button-prev {\n    left: -10px; }\n  .small-carousel .swiper-button-next {\n    right: -10px; }\n  .mat-card.zoom-viewer {\n  position: absolute;\n  display: none;\n  background-repeat: no-repeat;\n  padding: 8px;\n  z-index: 99; }\n  .mt-16 {\n  margin-top: 16px; }\n  .details button {\n  padding: 0;\n  min-width: 36px;\n  margin-left: 6px; }\n  .mat-list.reviews .mat-list-item .mat-list-avatar.review-author {\n  width: 80px;\n  height: 80px; }\n  .mat-list.reviews .mat-list-item .mat-line.text {\n  white-space: unset;\n  font-style: italic;\n  margin: 10px 0; }\n  .full-desc {\n  padding: 30px 40px; }\n  @media (min-width: 1280px) {\n  .mat-card.product-image img {\n    cursor: zoom-in;\n    cursor: url(\"data:image/svg+xml,%3Csvg fill%3D%22%23000000%22 height%3D%2236%22 viewBox%3D%220 0 24 24%22 width%3D%2236%22 xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%0D    %3Cpath d%3D%22M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z%22%2F%3E%0D    %3Cpath d%3D%22M0 0h24v24H0V0z%22 fill%3D%22none%22%2F%3E%0D    %3Cpath d%3D%22M12 10h-2v2H9v-2H7V9h2V7h1v2h2v1z%22%2F%3E%0D%3C%2Fsvg%3E\") 10 10, zoom-in; } }\n"

/***/ }),

/***/ "./src/app/pages/products/product/product.component.ts":
/*!*************************************************************!*\
  !*** ./src/app/pages/products/product/product.component.ts ***!
  \*************************************************************/
/*! exports provided: ProductComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductComponent", function() { return ProductComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var ngx_swiper_wrapper__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ngx-swiper-wrapper */ "./node_modules/ngx-swiper-wrapper/dist/ngx-swiper-wrapper.es5.js");
/* harmony import */ var _theme_utils_app_validators__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../../theme/utils/app-validators */ "./src/app/theme/utils/app-validators.ts");
/* harmony import */ var _product_zoom_product_zoom_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./product-zoom/product-zoom.component */ "./src/app/pages/products/product/product-zoom/product-zoom.component.ts");
/* harmony import */ var _theme_utils_httpInterceptor_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../../../theme/utils/httpInterceptor.service */ "./src/app/theme/utils/httpInterceptor.service.ts");
/* harmony import */ var _shared_products_carousel_product_dialog_product_dialog_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../../../shared/products-carousel/product-dialog/product-dialog.component */ "./src/app/shared/products-carousel/product-dialog/product-dialog.component.ts");
/* harmony import */ var _local_storage__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../../../local.storage */ "./src/app/local.storage.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};










var ProductComponent = /** @class */ (function () {
    function ProductComponent(router, localStorage, snackBar, httpUtil, activatedRoute, dialog, formBuilder) {
        this.router = router;
        this.localStorage = localStorage;
        this.snackBar = snackBar;
        this.httpUtil = httpUtil;
        this.activatedRoute = activatedRoute;
        this.dialog = dialog;
        this.formBuilder = formBuilder;
        this.config = {};
        this.images = [];
        this.vendorINfo = {
            name: "",
            email: "",
            phone: "",
            url: "",
        };
    }
    ProductComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.activatedRoute.params.subscribe(function (params) {
            _this.getProductById(params['id']);
        });
        this.form = this.formBuilder.group({
            'review': [null, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required],
            'name': [null, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].compose([_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].minLength(4)])],
            'email': [null, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].compose([_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _theme_utils_app_validators__WEBPACK_IMPORTED_MODULE_5__["emailValidator"]])]
        });
    };
    ProductComponent.prototype.ngAfterViewInit = function () {
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
        };
    };
    ProductComponent.prototype.getProductById = function (id) {
        var _this = this;
        this.httpUtil.get('/v1/products/' + id, function (data) {
            if (data.statusCode == "200") {
                if (data.code == "000") {
                    _this.product = data.data;
                    if (!_this.product.tags) {
                        _this.product.tags = new Array();
                    }
                    if (!_this.product.pictures) {
                        _this.product.pictures = new Array();
                    }
                    _this.images = _this.product.pictures;
                    if (_this.product.pictures.length > 0) {
                        _this.image = _this.product.pictures[0].url;
                        _this.zoomImage = _this.product.pictures[0].url;
                    }
                    _this.httpUtil.get('/v1/vendors/' + _this.product.vendor, function (data) {
                        if (data.statusCode == "200") {
                            if (data.code == "000") {
                                _this.vendorINfo.name = data.data.name;
                                _this.vendorINfo.email = data.data.email;
                                _this.vendorINfo.phone = data.data.contact;
                                _this.vendorINfo.url = data.data.url;
                            }
                            else {
                                _this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                            }
                        }
                        else {
                            _this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                        }
                    }, function (err) {
                        _this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                    });
                    setTimeout(function () {
                        _this.config.observer = true;
                        // this.directiveRef.setIndex(0);
                    });
                }
                else {
                    _this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                }
            }
            else {
                _this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
            }
        }, function (err) {
            _this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
        });
    };
    ProductComponent.prototype.open = function () {
        window.open(this.product.descUrl, '_blank');
    };
    ProductComponent.prototype.getRelatedProducts = function () {
        // this.appService.getProducts("",0).subscribe(data => {
        //   this.relatedProducts = data;
        // })
    };
    ProductComponent.prototype.selectImage = function (image) {
        this.image = image;
        this.zoomImage = image;
    };
    ProductComponent.prototype.onMouseMove = function (e) {
        if (window.innerWidth >= 1280) {
            var image, offsetX, offsetY, x, y, zoomer;
            image = e.currentTarget;
            offsetX = e.offsetX;
            offsetY = e.offsetY;
            x = offsetX / image.offsetWidth * 100;
            y = offsetY / image.offsetHeight * 100;
            zoomer = this.zoomViewer.nativeElement.children[0];
            if (zoomer) {
                zoomer.style.backgroundPosition = x + '% ' + y + '%';
                zoomer.style.display = "block";
                zoomer.style.height = image.height + 'px';
                zoomer.style.width = image.width + 'px';
            }
        }
    };
    ProductComponent.prototype.onMouseLeave = function (event) {
        this.zoomViewer.nativeElement.children[0].style.display = "none";
    };
    ProductComponent.prototype.openZoomViewer = function () {
        this.dialog.open(_product_zoom_product_zoom_component__WEBPACK_IMPORTED_MODULE_6__["ProductZoomComponent"], {
            data: this.zoomImage,
            panelClass: 'zoom-dialog'
        });
    };
    ProductComponent.prototype.openProductDialog = function () {
        if (this.localStorage.get("userName")) {
            var dialogRef = this.dialog.open(_shared_products_carousel_product_dialog_product_dialog_component__WEBPACK_IMPORTED_MODULE_8__["ProductDialogComponent"], {
                data: this.product,
                panelClass: 'product-dialog'
            });
            dialogRef.afterClosed().subscribe(function (product) {
            });
        }
        else {
            this.router.navigate(['/sign-in']);
        }
    };
    ProductComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    ProductComponent.prototype.onSubmit = function (values) {
        if (this.form.valid) {
            //email sent
        }
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])('zoomViewer'),
        __metadata("design:type", Object)
    ], ProductComponent.prototype, "zoomViewer", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])(ngx_swiper_wrapper__WEBPACK_IMPORTED_MODULE_4__["SwiperDirective"]),
        __metadata("design:type", ngx_swiper_wrapper__WEBPACK_IMPORTED_MODULE_4__["SwiperDirective"])
    ], ProductComponent.prototype, "directiveRef", void 0);
    ProductComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-product',
            template: __webpack_require__(/*! ./product.component.html */ "./src/app/pages/products/product/product.component.html"),
            styles: [__webpack_require__(/*! ./product.component.scss */ "./src/app/pages/products/product/product.component.scss")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"], _local_storage__WEBPACK_IMPORTED_MODULE_9__["LocalStorage"], _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBar"], _theme_utils_httpInterceptor_service__WEBPACK_IMPORTED_MODULE_7__["HttpInterceptorService"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["ActivatedRoute"], _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatDialog"], _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormBuilder"]])
    ], ProductComponent);
    return ProductComponent;
}());



/***/ }),

/***/ "./src/app/pages/products/products.component.html":
/*!********************************************************!*\
  !*** ./src/app/pages/products/products.component.html ***!
  \********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-sidenav-container>\r\n    <!--<mat-sidenav #sidenav [opened]=\"sidenavOpen\" [mode]=\"sidenavOpen ? 'side' : 'over'\" class=\"filter-sidenav\" perfectScrollbar>-->\r\n\r\n        <!--<mat-accordion displayMode=\"flat\" multi=\"true\">-->\r\n            <!--<mat-expansion-panel expanded>-->\r\n                <!--<mat-expansion-panel-header>-->\r\n                    <!--<mat-panel-title>-->\r\n                        <!--<h4>Categories</h4>-->\r\n                    <!--</mat-panel-title>-->\r\n                <!--</mat-expansion-panel-header>-->\r\n                <!--<div class=\"categories-wrapper categories-dropdown\" perfectScrollbar>-->\r\n                    <!--<app-category-list [categories]=\"categories\" [categoryParentId]=\"0\" (change)=\"onChangeCategory($event)\"></app-category-list>-->\r\n                <!--</div>-->\r\n            <!--</mat-expansion-panel>-->\r\n\r\n            <!--<mat-expansion-panel expanded>-->\r\n                <!--<mat-expansion-panel-header>-->\r\n                    <!--<mat-panel-title>-->\r\n                        <!--<h4>Price</h4>-->\r\n                    <!--</mat-panel-title>-->\r\n                <!--</mat-expansion-panel-header>-->\r\n                <!--<div fxLayout=\"row\" fxLayoutAlign=\"space-between center\" class=\"text-muted\">-->\r\n                    <!--<span>From: <b>${{priceFrom}}</b></span>-->\r\n                    <!--<span>To: <b>${{priceTo}}</b></span>-->\r\n                <!--</div>-->\r\n                <!--<div fxLayout=\"row\" fxLayoutAlign=\"space-between center\" class=\"filter-price\">-->\r\n                    <!--<mat-slider color=\"primary\" max=\"2000\" min=\"1\" thumb-label=\"true\" [(ngModel)]=\"priceFrom\"></mat-slider>-->\r\n                    <!--<mat-slider color=\"warn\" max=\"2000\" min=\"1\" thumb-label=\"true\" [(ngModel)]=\"priceTo\"></mat-slider>-->\r\n                <!--</div>-->\r\n            <!--</mat-expansion-panel>-->\r\n\r\n            <!--<mat-expansion-panel expanded>-->\r\n                <!--<mat-expansion-panel-header>-->\r\n                    <!--<mat-panel-title>-->\r\n                        <!--<h4>Color</h4>-->\r\n                    <!--</mat-panel-title>-->\r\n                <!--</mat-expansion-panel-header>-->\r\n                <!--<div fxLayout=\"row wrap\" fxLayoutAlign=\"space-between center\" class=\"filter-buttons\">-->\r\n                    <!--<button mat-raised-button *ngFor=\"let color of colors\" [style.background]=\"color\">&nbsp;</button>-->\r\n                <!--</div>-->\r\n            <!--</mat-expansion-panel>-->\r\n\r\n            <!--<mat-expansion-panel expanded>-->\r\n                <!--<mat-expansion-panel-header>-->\r\n                    <!--<mat-panel-title>-->\r\n                        <!--<h4>Size</h4>-->\r\n                    <!--</mat-panel-title>-->\r\n                <!--</mat-expansion-panel-header>-->\r\n                <!--<div fxLayout=\"row wrap\" fxLayoutAlign=\"space-between center\" class=\"filter-buttons\">-->\r\n                    <!--<button mat-raised-button *ngFor=\"let size of sizes\">{{size}}</button>-->\r\n                <!--</div>-->\r\n            <!--</mat-expansion-panel>-->\r\n\r\n            <!--<mat-expansion-panel expanded>-->\r\n                <!--<mat-expansion-panel-header>-->\r\n                    <!--<mat-panel-title>-->\r\n                        <!--<h4>Condition</h4>-->\r\n                    <!--</mat-panel-title>-->\r\n                <!--</mat-expansion-panel-header>-->\r\n                <!--<div fxLayout=\"column\">-->\r\n                    <!--<mat-checkbox color=\"primary\">New</mat-checkbox>-->\r\n                    <!--<mat-checkbox color=\"primary\">Used</mat-checkbox>-->\r\n                    <!--<mat-checkbox color=\"primary\">Not Specified</mat-checkbox>-->\r\n                <!--</div>-->\r\n            <!--</mat-expansion-panel>-->\r\n\r\n            <!--<mat-expansion-panel expanded>-->\r\n                <!--<mat-expansion-panel-header>-->\r\n                    <!--<mat-panel-title>-->\r\n                        <!--<h4>Brands</h4>-->\r\n                    <!--</mat-panel-title>-->\r\n                <!--</mat-expansion-panel-header>-->\r\n                <!--<div fxLayout=\"row wrap\" fxLayoutAlign=\"space-between center\" class=\"filter-brands\">-->\r\n                    <!--<button mat-raised-button *ngFor=\"let brand of brands\">-->\r\n                        <!--<img [src]=\"brand.image\"/>-->\r\n                    <!--</button>-->\r\n                <!--</div>-->\r\n            <!--</mat-expansion-panel>-->\r\n\r\n            <!--<mat-expansion-panel expanded>-->\r\n                <!--<mat-expansion-panel-header>-->\r\n                    <!--<mat-panel-title>-->\r\n                        <!--<h4>Now starting at $1379</h4>-->\r\n                    <!--</mat-panel-title>-->\r\n                <!--</mat-expansion-panel-header>-->\r\n                <!--<div fxLayout=\"row wrap\" fxLayoutAlign=\"center center\">-->\r\n                   <!--<a [routerLink]=\"['/products/electronics']\"><img src=\"assets/images/products/probook/2-medium.png\" alt=\"\" class=\"mw-100\"></a>-->\r\n                <!--</div>-->\r\n            <!--</mat-expansion-panel>-->\r\n\r\n        <!--</mat-accordion>-->\r\n\r\n    <!--</mat-sidenav>-->\r\n    <mat-sidenav-content class=\"all-products\" ngClass.gt-sm=\"p-left\">\r\n        <div #util fxLayout=\"row\" fxLayout.xs=\"column\" fxLayoutAlign=\"space-between center\" class=\"filter-row mat-elevation-z1 text-muted\">\r\n            <!--<button *ngIf=\"!sidenavOpen\" mat-icon-button (click)=\"sidenav.toggle()\">-->\r\n                <!--<mat-icon>more_vert</mat-icon>-->\r\n            <!--</button>-->\r\n            <div>\r\n                <a mat-button [matMenuTriggerFor]=\"sortMenu\" #sortMenuTrigger=\"matMenuTrigger\">\r\n                    {{sort.name}}<mat-icon class=\"mat-icon-sm caret\">arrow_drop_down</mat-icon>\r\n                </a>\r\n                <mat-menu #sortMenu=\"matMenu\" xPosition=\"before\" class=\"app-dropdown\">\r\n                    <span (mouseleave)=\"sortMenuTrigger.closeMenu()\">\r\n                        <button mat-menu-item *ngFor=\"let sorts of sortings\" (click)=\"changeSorting(sorts)\">\r\n                            <span [class]=\"sorts.value==sort.value?'selected':''\">{{sorts.name}}</span>\r\n                        </button>\r\n                    </span>\r\n                </mat-menu>\r\n            </div>\r\n            <div fxLayout=\"row\">\r\n                <a mat-button [matMenuTriggerFor]=\"countsMenu\" #countsMenuTrigger=\"matMenuTrigger\" fxFlex=\"100\" fxFlex.gt-xs=\"40\" style=\"padding: 10px 16px\">\r\n                     {{queryType.name}}<mat-icon class=\"mat-icon-sm caret\">arrow_drop_down</mat-icon>\r\n                </a>\r\n                <mat-menu #countsMenu=\"matMenu\" xPosition=\"before\" class=\"app-dropdown\">\r\n                    <span (mouseleave)=\"countsMenuTrigger.closeMenu()\">\r\n                        <button mat-menu-item *ngFor=\"let query of queryTypes\" (click)=\"changeQuery(query)\" >\r\n                            <span [class]=\"query.value==queryType.value?'selected':''\">{{query.name}}</span>\r\n                        </button>\r\n                    </span>\r\n                </mat-menu>\r\n                <form class=\"example-form\" fxFlex=\"100\" fxFlex.gt-xs=\"60\" *ngIf=\"queryType.value!=''&&queryType.value!='typeName'&&queryType.value!='categoryName'\">\r\n                    <mat-form-field class=\"example-full-width\">\r\n                        <input matInput  name=\"qu\" [(ngModel)]=\"qu\" (change)=\"query($event)\">\r\n                    </mat-form-field>\r\n                </form>\r\n                <div *ngIf=\"queryType.value!=''&&queryType.value!='name'&&queryType.value!='vendorName'\">\r\n                    <a mat-button [matMenuTriggerFor]=\"countsMenu\" #countsMenuTrigger=\"matMenuTrigger\" fxFlex=\"100\" fxFlex.gt-xs=\"40\" style=\"padding: 10px 16px\">\r\n                        {{querySelect.name}}<mat-icon class=\"mat-icon-sm caret\">arrow_drop_down</mat-icon>\r\n                    </a>\r\n                    <mat-menu #countsMenu=\"matMenu\" xPosition=\"before\" class=\"app-dropdown\">\r\n                    <span (mouseleave)=\"countsMenuTrigger.closeMenu()\">\r\n                        <button mat-menu-item *ngFor=\"let queryS of querySelects\" (click)=\"query(queryS)\" >\r\n                            <span >{{queryS.name}}</span>\r\n                        </button>\r\n                    </span>\r\n                    </mat-menu>\r\n                </div>\r\n            </div>\r\n        </div>\r\n\r\n        <div  fxLayout=\"row wrap\" class=\"products-wrapper\">\r\n            <div *ngFor=\"let product of products | paginate: { itemsPerPage: count, currentPage: page,totalItems:total }\" fxFlex=\"100\" [fxFlex.gt-sm]=\"'20'\" fxFlex.sm=\"50\" class=\"col\">\r\n                <mat-card class=\"product-item text-center\">\r\n                    <mat-chip-list *ngIf=\"product.typeName\">\r\n                        <mat-chip color=\"warn\" selected=\"true\">{{product.typeName}}</mat-chip>\r\n                    </mat-chip-list>\r\n                    <a [routerLink]=\"['/', product.id, product.name]\" class=\"image-link\">\r\n                        <img [src]=\"product.icon\" alt=\"\">\r\n                    </a>\r\n                    <a [routerLink]=\"['/', product.id, product.name]\" class=\"title text-truncate\">\r\n                        {{product.name}}\r\n                    </a>\r\n                    <p style=\" word-wrap:break-word;\r\n                                word-break:break-all;\r\n                                overflow:hidden;\r\n                                text-overflow: ellipsis;\r\n                                display:-webkit-box;\r\n                                -webkit-line-clamp:2;\r\n                                -webkit-box-orient:vertical;\" class=\"py-1 text-muted \">{{product.shortDesc}}</p>\r\n                    <div fxLayout=\"row\" fxLayoutAlign=\"space-between center\" class=\"prices\">\r\n                        <div [fxLayout]=\"'column'\" fxFlex=\"100\" fxFlex.gt-xs=\"60\" [fxLayoutAlign]=\"'start'\">\r\n                            <div class=\"cartView\">\r\n                                <span>{{  product.categoryName}}</span>\r\n                            </div>\r\n                            <div class=\"cartView\">\r\n                                <span> {{product.vendorName }}</span>\r\n                            </div>\r\n                        </div>\r\n                        <div [fxLayout]=\"'column'\" fxFlex=\"100\" fxFlex.gt-xs=\"40\" [fxLayoutAlign]=\"'end'\">\r\n                            <div class=\"cartViewBution\">\r\n                                <button  color=\"primary\" class=\"mat-raised-button mat-primary\"  (click)=\"openProductDialog(product)\">\r\n                                    <span class=\"mat-button-wrapper\">GET</span>\r\n                                    <div class=\"mat-button-ripple mat-ripple\"></div>\r\n                                    <div class=\"mat-button-focus-overlay\"></div>\r\n                                </button>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                    <div class=\"divider mt-2\"></div>\r\n                    <div class=\"icons\">\r\n                        <app-tags [tags]=\"product.tags\"></app-tags>\r\n                        <!--<app-controls [product]=\"product\" (onOpenProductDialog)=\"openProductDialog(product)\"></app-controls>-->\r\n                    </div>\r\n                </mat-card>\r\n            </div>\r\n        </div>\r\n\r\n        <!--<div *ngIf=\"viewType == 'list'\" fxLayout=\"row wrap\" class=\"products-wrapper\">-->\r\n            <!--<div  *ngFor=\"let product of products | paginate: { itemsPerPage: count, currentPage: page,totalItems:20 }\" fxFlex=\"100\" class=\"col\">-->\r\n                <!--<mat-card class=\"product-item\">-->\r\n                    <!--<div fxLayout=\"row wrap\">-->\r\n                        <!--<div fxFlex=\"100\" fxFlex.gt-xs=\"40\" class=\"p-2\">-->\r\n                            <!--<a [routerLink]=\"['/', product.id, product.name]\" class=\"image-link\">-->\r\n                                <!--<img [src]=\"product.icon\" alt=\"\">-->\r\n                            <!--</a>-->\r\n                        <!--</div>-->\r\n                        <!--<div fxFlex=\"100\" fxFlex.gt-xs=\"60\" class=\"p-2\">-->\r\n                            <!--<h4 class=\"category text-muted\">{{ product.category }}</h4>-->\r\n                            <!--<a [routerLink]=\"['/', product.id, product.name]\" class=\"title\">-->\r\n                                <!--{{product.name}}-->\r\n                            <!--</a>-->\r\n                            <!--<p class=\"py-1 text-muted lh\">{{product.description}}</p>-->\r\n                            <!--<div class=\"divider\"></div>-->\r\n                            <!--<div class=\"icons\">-->\r\n                                <!--<app-controls [product]=\"product\" (onOpenProductDialog)=\"openProductDialog(product)\"></app-controls>-->\r\n                            <!--</div>-->\r\n                        <!--</div>-->\r\n                    <!--</div>-->\r\n                <!--</mat-card>-->\r\n            <!--</div>-->\r\n        <!--</div>-->\r\n\r\n        <div *ngIf=\"products?.length > 0\" fxLayout=\"row wrap\">\r\n            <div fxFlex=\"100\">\r\n                <mat-card class=\"p-0 text-center\">\r\n                    <pagination-controls class=\"product-pagination\" autoHide=\"true\" maxSize=\"5\" (pageChange)=\"onPageChanged($event)\" nextLabel=\"下一页\" previousLabel=\"上一页\"></pagination-controls>\r\n                </mat-card>\r\n            </div>\r\n        </div>\r\n\r\n    </mat-sidenav-content>\r\n</mat-sidenav-container>\r\n"

/***/ }),

/***/ "./src/app/pages/products/products.component.scss":
/*!********************************************************!*\
  !*** ./src/app/pages/products/products.component.scss ***!
  \********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".filter-sidenav {\n  width: 280px;\n  padding: 2px; }\n  .filter-sidenav .mat-expansion-panel-header-title {\n    text-transform: uppercase; }\n  .filter-row {\n  background: #fff;\n  padding: 8px 12px; }\n  .all-products {\n  min-height: 400px;\n  padding: 2px;\n  overflow: hidden; }\n  .all-products.p-left {\n    padding-left: 16px; }\n  .products-wrapper {\n  margin: 8px -8px; }\n  .products-wrapper .col {\n    padding: 8px; }\n  .categories-wrapper {\n  position: relative;\n  max-height: 300px; }\n  .filter-buttons button {\n  min-width: 36px;\n  margin: 0 6px 10px 0;\n  padding: 0; }\n  .filter-price .mat-slider-horizontal {\n  min-width: 110px; }\n  .filter-brands button {\n  width: 66px;\n  height: 44px;\n  min-width: 66px;\n  margin: 0 6px 10px 0;\n  padding: 0px;\n  overflow: hidden; }\n  .filter-brands button img {\n    width: 66px;\n    height: 44px; }\n  :host .product-item .mat-chip-list {\n  right: unset;\n  left: 8px;\n  top: 8px; }\n  .cartView {\n  text-align: left;\n  display: inline-block;\n  overflow: hidden;\n  white-space: nowrap;\n  text-overflow: ellipsis; }\n  .cartViewBution {\n  text-align: right; }\n  .cartViewBution button {\n    min-width: 58px; }\n  .sfixed {\n  position: fixed;\n  top: 0px;\n  left: 0px;\n  right: 0px;\n  width: 100%;\n  margin-left: auto;\n  margin-right: auto;\n  z-index: 9990; }\n  .selected {\n  color: #1976d2; }\n  .example-full-width {\n  width: 100%; }\n"

/***/ }),

/***/ "./src/app/pages/products/products.component.ts":
/*!******************************************************!*\
  !*** ./src/app/pages/products/products.component.ts ***!
  \******************************************************/
/*! exports provided: ProductsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductsComponent", function() { return ProductsComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _shared_products_carousel_product_dialog_product_dialog_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../shared/products-carousel/product-dialog/product-dialog.component */ "./src/app/shared/products-carousel/product-dialog/product-dialog.component.ts");
/* harmony import */ var _app_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../app.service */ "./src/app/app.service.ts");
/* harmony import */ var _theme_utils_httpInterceptor_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../theme/utils/httpInterceptor.service */ "./src/app/theme/utils/httpInterceptor.service.ts");
/* harmony import */ var _local_storage__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../local.storage */ "./src/app/local.storage.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var ProductsComponent = /** @class */ (function () {
    function ProductsComponent(localStorage, renderer, snackBar, httpUtil, activatedRoute, appService, dialog, router) {
        this.localStorage = localStorage;
        this.renderer = renderer;
        this.snackBar = snackBar;
        this.httpUtil = httpUtil;
        this.activatedRoute = activatedRoute;
        this.appService = appService;
        this.dialog = dialog;
        this.router = router;
        this.sidenavOpen = true;
        this.viewType = 'grid';
        this.viewCol = 20;
        this.queryTypes = [
            { "name": "综合查询", "value": "" },
            { "name": "商品名称", "value": "name" },
            { "name": "供应商名称", "value": "vendorName" },
            { name: "Type", value: "typeName" },
            { name: "Category", value: "categoryName" },
        ];
        this.type = "";
        this.types = [];
        this.category = "";
        this.categorys = [];
        this.querySelect = "请选择";
        this.querySelects = [];
        this.total = 0;
        this.sortings = [
            { name: "综合排序", value: "" },
            { name: "商品名称", value: "name" },
            { name: "Type", value: "typeName" },
            { name: "Category", value: "categoryName" },
            { name: "Vendor", value: "vendorName" },
        ];
        this.postBody = {
            "start": 1,
            "name": "",
            "typeName": "",
            "categoryName": "",
            "vendorName": ""
        };
        this.products = [];
        this.brands = [];
        this.colors = ["#5C6BC0", "#66BB6A", "#EF5350", "#BA68C8", "#FF4081", "#9575CD", "#90CAF9", "#B2DFDB", "#DCE775", "#FFD740", "#00E676", "#FBC02D", "#FF7043", "#F5F5F5", "#000000"];
        this.sizes = ["S", "M", "L", "XL", "2XL", "32", "36", "38", "46", "52", "13.3\"", "15.4\"", "17\"", "21\"", "23.4\""];
        this.qu = "";
    }
    ProductsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.renderer.listen("window", "scroll", function (event) {
            var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
            if (scrollTop > 345) {
                _this.renderer.addClass(_this.util.nativeElement, "sfixed");
            }
            else {
                _this.renderer.removeClass(_this.util.nativeElement, "sfixed");
            }
        });
        // window.onscroll = function(e){
        //     var scrolltop=document.documentElement.scrollTop||document.body.scrollTop;
        //     console.log(that.util.contentTop)
        //     console.log(scrolltop);
        // }
        this.count = 15;
        this.sort = this.sortings[0];
        this.queryType = this.queryTypes[0];
        this.sub = this.activatedRoute.params.subscribe(function (params) {
            //console.log(params['name']);
        });
        if (window.innerWidth < 960) {
            this.sidenavOpen = false;
        }
        ;
        if (window.innerWidth < 1280) {
            this.viewCol = 33.3;
        }
        ;
        this.getTypes();
        this.getCategorys();
        this.getBrands();
        this.getAllProducts();
    };
    ProductsComponent.prototype.getAllProducts = function () {
        var _this = this;
        this.httpUtil.post('/v1/products/list', this.postBody, function (data) {
            if (data.statusCode == "200") {
                if (data.code == "000") {
                    _this.total = data.data['size:'];
                    _this.products = data.data['products:'];
                }
                else {
                    _this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
                }
            }
            else {
                _this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
            }
        }, function (err) {
            _this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
        });
    };
    ProductsComponent.prototype.getCategories = function () {
        var _this = this;
        if (this.appService.Data.categories.length == 0) {
            this.appService.getCategories().subscribe(function (data) {
                _this.categories = data;
                _this.appService.Data.categories = data;
            });
        }
        else {
            this.categories = this.appService.Data.categories;
        }
    };
    ProductsComponent.prototype.getBrands = function () {
        this.brands = this.appService.getBrands();
    };
    ProductsComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    ProductsComponent.prototype.onWindowResize = function () {
        (window.innerWidth < 960) ? this.sidenavOpen = false : this.sidenavOpen = true;
        (window.innerWidth < 1280) ? this.viewCol = 33.3 : this.viewCol = 25;
    };
    ProductsComponent.prototype.changeQuery = function (query) {
        this.queryType = query;
        if (query.value == "typeName") {
            this.querySelect = this.type;
            this.querySelects = this.types;
            this.querySelects.unshift({ "name": "请选择", "value": "" });
        }
        if (query.value == "categoryName") {
            this.querySelect = this.category;
            this.querySelects = this.categorys;
            this.querySelects.unshift({ "name": "请选择", "value": "" });
        }
        if (query.value == "") {
            this.postBody.typeName = "";
            this.postBody.name = "";
            this.postBody.vendorName = "";
            this.postBody.categoryName = "";
            this.postBody.start = 1;
            this.page = 1;
            this.getAllProducts();
        }
    };
    ProductsComponent.prototype.changeSorting = function (sort) {
        this.sort = sort;
        if (sort.value == "") {
            delete this.postBody['asc'];
        }
        else {
            this.postBody['asc'] = sort.value;
        }
        this.page = 1;
        this.postBody.start = 1;
        this.getAllProducts();
    };
    ProductsComponent.prototype.changeViewType = function (viewType, viewCol) {
        this.viewType = viewType;
        this.viewCol = viewCol;
    };
    ProductsComponent.prototype.openProductDialog = function (product) {
        var _this = this;
        if (this.localStorage.get("userName")) {
            var dialogRef = this.dialog.open(_shared_products_carousel_product_dialog_product_dialog_component__WEBPACK_IMPORTED_MODULE_3__["ProductDialogComponent"], {
                data: product,
                panelClass: 'product-dialog'
            });
            dialogRef.afterClosed().subscribe(function (product) {
                if (product) {
                    _this.router.navigate(['/products', product.id, product.name]);
                }
            });
        }
        else {
            this.router.navigate(['/sign-in']);
        }
    };
    ProductsComponent.prototype.onPageChanged = function (event) {
        this.page = event;
        this.postBody.start = this.page;
        this.getAllProducts();
        window.scrollTo(0, 0);
    };
    ProductsComponent.prototype.getTypes = function () {
        var _this = this;
        this.httpUtil.get('/v1/types', function (data) {
            _this.type = { "name": "请选择", "value": "" };
            _this.types = data;
        }, function (err) {
            _this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
        });
    };
    ProductsComponent.prototype.getCategorys = function () {
        var _this = this;
        this.httpUtil.get('/v1/categorys', function (data) {
            _this.category = { "name": "请选择", "value": "" };
            _this.categorys = data;
        }, function (err) {
            _this.snackBar.open(err, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
        });
    };
    ProductsComponent.prototype.onChangeCategory = function (event) {
        if (event.target) {
            this.router.navigate(['/products', event.target.innerText.toLowerCase()]);
        }
    };
    ProductsComponent.prototype.query = function (event) {
        this.postBody.typeName = "";
        this.postBody.name = "";
        this.postBody.vendorName = "";
        this.postBody.categoryName = "";
        if (this.queryType.value == 'name') {
            this.postBody.name = this.qu;
        }
        if (this.queryType.value == 'vendorName') {
            this.postBody.vendorName = this.qu;
        }
        if (this.queryType.value == 'categoryName') {
            this.postBody.categoryName = event.name != "请选择" ? event.name : '';
            this.querySelect = event;
        }
        if (this.queryType.value == 'typeName') {
            this.postBody.typeName = event.name != "请选择" ? event.name : '';
            this.querySelect = event;
        }
        this.page = 1;
        this.postBody.start = 1;
        this.getAllProducts();
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])('sidenav'),
        __metadata("design:type", Object)
    ], ProductsComponent.prototype, "sidenav", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])('util'),
        __metadata("design:type", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"])
    ], ProductsComponent.prototype, "util", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"])('window:resize'),
        __metadata("design:type", Function),
        __metadata("design:paramtypes", []),
        __metadata("design:returntype", void 0)
    ], ProductsComponent.prototype, "onWindowResize", null);
    ProductsComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-products',
            template: __webpack_require__(/*! ./products.component.html */ "./src/app/pages/products/products.component.html"),
            styles: [__webpack_require__(/*! ./products.component.scss */ "./src/app/pages/products/products.component.scss")]
        }),
        __metadata("design:paramtypes", [_local_storage__WEBPACK_IMPORTED_MODULE_6__["LocalStorage"], _angular_core__WEBPACK_IMPORTED_MODULE_0__["Renderer2"], _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSnackBar"], _theme_utils_httpInterceptor_service__WEBPACK_IMPORTED_MODULE_5__["HttpInterceptorService"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["ActivatedRoute"], _app_service__WEBPACK_IMPORTED_MODULE_4__["AppService"], _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatDialog"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], ProductsComponent);
    return ProductsComponent;
}());



/***/ }),

/***/ "./src/app/pages/products/products.module.ts":
/*!***************************************************!*\
  !*** ./src/app/pages/products/products.module.ts ***!
  \***************************************************/
/*! exports provided: routes, ProductsModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "routes", function() { return routes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductsModule", function() { return ProductsModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var ngx_swiper_wrapper__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ngx-swiper-wrapper */ "./node_modules/ngx-swiper-wrapper/dist/ngx-swiper-wrapper.es5.js");
/* harmony import */ var ngx_pagination__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ngx-pagination */ "./node_modules/ngx-pagination/dist/ngx-pagination.js");
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _theme_pipes_pipes_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../../theme/pipes/pipes.module */ "./src/app/theme/pipes/pipes.module.ts");
/* harmony import */ var _products_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./products.component */ "./src/app/pages/products/products.component.ts");
/* harmony import */ var _product_product_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./product/product.component */ "./src/app/pages/products/product/product.component.ts");
/* harmony import */ var _product_product_zoom_product_zoom_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./product/product-zoom/product-zoom.component */ "./src/app/pages/products/product/product-zoom/product-zoom.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};











var routes = [
    // { path: '', component: ProductsComponent, pathMatch: 'full' },
    // { path: ':name', component: ProductsComponent },
    { path: ':id/:name', component: _product_product_component__WEBPACK_IMPORTED_MODULE_9__["ProductComponent"], data: { breadcrumb: '商品详情' } }
];
var ProductsModule = /** @class */ (function () {
    function ProductsModule() {
    }
    ProductsModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes),
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["ReactiveFormsModule"],
                ngx_swiper_wrapper__WEBPACK_IMPORTED_MODULE_4__["SwiperModule"],
                ngx_pagination__WEBPACK_IMPORTED_MODULE_5__["NgxPaginationModule"],
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_6__["SharedModule"],
                _theme_pipes_pipes_module__WEBPACK_IMPORTED_MODULE_7__["PipesModule"]
            ],
            declarations: [
                _products_component__WEBPACK_IMPORTED_MODULE_8__["ProductsComponent"],
                _product_product_component__WEBPACK_IMPORTED_MODULE_9__["ProductComponent"],
                _product_product_zoom_product_zoom_component__WEBPACK_IMPORTED_MODULE_10__["ProductZoomComponent"]
            ],
            entryComponents: [
                _product_product_zoom_product_zoom_component__WEBPACK_IMPORTED_MODULE_10__["ProductZoomComponent"]
            ]
        })
    ], ProductsModule);
    return ProductsModule;
}());



/***/ })

}]);
//# sourceMappingURL=app-pages-home-home-module.js.map