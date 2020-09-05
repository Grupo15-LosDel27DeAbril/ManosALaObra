/** REVISAR Y MODIFICAR!**/

import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { DataService } from '../data.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {

    constructor(private api: ApiService, private http: HttpClient, public appComp: AppComponent, public data: DataService){

    }

    ngOnInit(){

    }
}
