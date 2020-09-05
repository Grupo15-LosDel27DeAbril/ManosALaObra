import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { DataService } from '../data.service';
import { Producto } from '../producto';
import { ApiService } from '../api.service'; 

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

    selectedDonacion: Producto;
    productos: Producto[] = []
    constructor(private api: ApiService, private http: HttpClient, public appComp: AppComponent, public data: DataService){

    }

    ngOnInit(){
        this.api.getDonacionesAPI$().subscribe(resp => { this.productos = resp;});
        console.log("Save button is clicked!", this.data.getProductos());

    }
}