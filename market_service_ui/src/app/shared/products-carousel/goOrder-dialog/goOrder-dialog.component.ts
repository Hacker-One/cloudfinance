import { Component, ViewEncapsulation, OnInit, Inject} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA, MatSnackBar} from '@angular/material';
import { SwiperConfigInterface } from 'ngx-swiper-wrapper';
import {  AppService } from '../../../app.service';
import { Product } from '../../../app.models';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-goOrder-dialog',
  templateUrl: './goOrder-dialog.component.html',
  styleUrls: ['./goOrder-dialog.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class GoOrderDialogComponent implements OnInit {
  public config: SwiperConfigInterface = {};
  constructor( private router: Router,
            public dialogRef: MatDialogRef<GoOrderDialogComponent>) { }

  ngOnInit() {
  }
    public onInfoFormSubmit(values:Object):void {

    }
  goOrder(){
      this.close();
      this.router.navigate(['/orders']);

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
