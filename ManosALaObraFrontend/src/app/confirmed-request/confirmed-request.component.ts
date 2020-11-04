import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';

@Component({
    selector: 'app-confirmed-request',
    templateUrl: './confirmed-request.component.html',
    styleUrls: ['./confirmed-request.component.css']
  })
export class ConfirmedRequestComponent implements OnInit{

    constructor(public router: Router) { }

    ngOnInit(){

    }

    public volverAlHome(){
        this.router.navigateByUrl('login');
    }
}