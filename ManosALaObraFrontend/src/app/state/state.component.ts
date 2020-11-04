import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Producto } from '../producto';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AppComponent } from '../app.component';
import { DataService } from '../data.service';
//import { Injectable } from '@angular/core';

@Component({
    selector: 'app-state',
    templateUrl: './state.component.html',
    styleUrls: ['./state.component.css']
})

export class StateComponent implements OnInit{
    
    constructor(public appComp: AppComponent, private route: Router, public data: DataService){}

    ngOnInit(){

    }

    public volverAlHome(){
        this.route.navigateByUrl('home');
    }
}