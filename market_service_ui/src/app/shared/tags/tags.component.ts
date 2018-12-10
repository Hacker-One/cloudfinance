import {Component, OnInit, Input, Output, EventEmitter, OnChanges} from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Data, AppService } from '../../app.service';
import { Product } from '../../app.models';

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.scss']
})
export class TagsComponent implements OnInit,OnChanges {
  @Input() tags: Array<any>=[];
  @Input() type: string;
  public tagicon=new Array();
  public align = 'center center';
  constructor() { }

  ngOnInit() {
    this.layoutAlign();
  }

  public layoutAlign(){
    if(this.type == 'all'){
      this.align = 'space-between center';
    }
    else if(this.type == 'wish'){
      this.align = 'start center';
    }
    else{
      this.align = 'center center';
    }
  }
    ngOnChanges(){
        if(this.tags){
            this.tagicon=new Array();
            for(var i=0;i<this.tags.length;i++){
                if(i==0){
                    this.tagicon.push({"icon":'settings_inpu',"name":this.tags[i].name});

                }
                if(i==1){
                    this.tagicon.push({"icon":'group',"name":this.tags[i].name});

                }
                if(i==2){
                    this.tagicon.push({"icon":'notifications',"name":this.tags[i].name});

                }
                if(i==3){
                    this.tagicon.push({"icon":'public',"name":this.tags[i].name});

                }
                if(i==4){
                    this.tagicon.push({"icon":'event_availab',"name":this.tags[i].name});

                }
            }
        }
    }
    ngAfterViewInit(){

    }





}
