import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { emailValidator, matchingPasswords } from '../../theme/utils/app-validators';
import {HttpInterceptorService} from '../../theme/utils/httpInterceptor.service';
import {LocalStorage} from '../../local.storage';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  loginForm: FormGroup;
  constructor(public localStorage:LocalStorage,public httpUtil:HttpInterceptorService,public formBuilder: FormBuilder, public router:Router, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      'loginName': ['', Validators.compose([Validators.required])],
      'pwd': ['', Validators.compose([Validators.required])]
    });


  }

  public onLoginFormSubmit(values:Object):void {
    if (this.loginForm.valid) {
        this.httpUtil.post('/v1/login',this.loginForm.value,data=>{
            if(data.statusCode=="200"){
                if(data.code=="000"){
                    this.localStorage.set("userName",this.loginForm.value.loginName);
                    this.router.navigate(['/']);
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
  }

}
