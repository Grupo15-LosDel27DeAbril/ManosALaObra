import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { MapComponent } from '../map/map.component';
import { AppComponent } from '../app.component';
import { DataService } from '../data.service';
import { Producto } from '../producto';
import { ApiService } from '../api.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { MapType } from '@angular/compiler';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

    zoom: number = 16;
    selectedDonacion: Producto;
    productos: Producto[] = []
    constructor(private api: ApiService, private http: HttpClient, public appComp: AppComponent, public data: DataService,private route: Router, private map: MapComponent){

    }

    ngOnInit(){
        this.api.getDonacionesAPI$().subscribe(resp => { this.productos = resp;});
        console.log("Save button is clicked!", this.data.getProductos());

    }

    public completarFormulario(){
         this.route.navigateByUrl('form');
    }

    public ubicacionEnMapa(producto){
        this.data.lat = producto.latitude;
        this.data.lng = producto.longitude;
        this.data.lugar = producto.lugar;
        this.route.navigateByUrl('map');
        //this.map.actualizarPosicion(producto.latitude, producto.longitude);
        //console.log('latitude es: ', producto.latitude);
        //console.log('longitude es: ', producto.longitude);
    }

    mostrarLatLng(producto){
        console.log("latitude es: " , producto.latitude);
        console.log("longitude es: ", producto.longitude);
    }
}