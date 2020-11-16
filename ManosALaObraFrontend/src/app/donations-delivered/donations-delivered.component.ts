import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { DataService } from '../data.service';
import { Producto } from '../producto';
import { ApiService } from '../api.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
    selector: 'app-donations-delivered',
    templateUrl: './donations-delivered.component.html',
    styleUrls: ['./donations-delivered.component.css']
})

export class DonationsDeliveredComponent implements OnInit{

    productos: Producto[] = [];

    constructor(private api: ApiService, private http: HttpClient, public appComp: AppComponent, public data: DataService,private route: Router){

    }

    ngOnInit(){
        this.api.getDonacionesEntregadas().subscribe(resp => {this.productos = resp; this.data.productosEntregados = resp});
        console.log("Las donaciones entregadas son: ", this.data.productos);
    }

    public ubicacionEnMapa(producto){
        this.data.lat = producto.latitude;
        this.data.lng = producto.longitude;
        this.data.lugar = producto.lugar;
        this.route.navigateByUrl('map');
    }

    public volverAlHome(){
        this.route.navigateByUrl('home');
    }
}