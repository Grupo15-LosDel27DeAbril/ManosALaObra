import { Injectable} from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { environment } from '../../enviroments/environment';
import { DataService } from '../data.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import * as Mapboxgl from 'mapbox-gl';
import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder';
import '@mapbox/mapbox-gl-geocoder/dist/mapbox-gl-geocoder.css';

@Component({
    selector: 'app-ubication',
    templateUrl: './ubication.component.html',
    styleUrls: ['./ubication.component.css']
})

@Injectable({
    providedIn: 'root'
})
export class UbicationComponent implements OnInit{

    mapa: Mapboxgl.Map;
    lng: number;
    lat: number;

    constructor(public data: DataService, public router: Router, public appComp: AppComponent){
        this.lng = data.userData.longitude;
        this.lat = data.userData.latitude;
    }

    ngOnInit(){
        navigator.geolocation.getCurrentPosition( position => {
        (Mapboxgl as any).accessToken = environment.mapboxKey;
        this.mapa = new Mapboxgl.Map({
            container: 'mapa-mapbox',
            style: 'mapbox://styles/mapbox/streets-v11',
            center: [ position.coords.longitude, position.coords.latitude ], // LNG, LAT
            zoom: 16.6
        });
        this.crearMarcador(position.coords.longitude, position.coords.latitude);
        var geocoder = new MapboxGeocoder({
            accessToken: (Mapboxgl as any).accessToken,
            mapboxgl: (Mapboxgl as any),
            marker: true,
        });

        this.mapa.addControl(geocoder);
        const markerSearch2 = new Mapboxgl.Marker({
              draggable: true
        })});
    }

    crearMarcador(lng: number, lat: number){

        const marker = new Mapboxgl.Marker({
            draggable: true
        }).setLngLat([lng, lat])
          .addTo(this.mapa);
    
          marker.on('drag', (onDragEnd) => {
              console.log(marker.getLngLat());
              console.log('la ultima coord es: ', marker.getLngLat());
              this.lng = marker.getLngLat().lng;
              this.lat = marker.getLngLat().lat;
              this.appComp.fijarUbicacion(marker.getLngLat().lat, marker.getLngLat().lng);
              this.data.userData.longitude = this.lng;
              this.data.userData.latitude = this.lat;
          })
    
      }

      public volverAlHome(){
        this.router.navigateByUrl('home');
    }
}