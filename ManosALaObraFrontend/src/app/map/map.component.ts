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
    selector: 'app-map',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.css']
})

@Injectable({
    providedIn: 'root'
})
export class MapComponent implements OnInit {

    mapa: Mapboxgl.Map;
    lng: number;
    lat: number;
    lugar: String;
    distancia: number;
    EARTH_RADIUS:number = 6371.0;

    constructor(public data: DataService, public router: Router, public appComp: AppComponent){
        this.lng = data.lng;
        this.lat = data.lat;
        this.lugar = data.lugar;
        this.distancia = this.calcularNumerito(this.lat, this.lng);
    }

    ngOnInit(){
  
      (Mapboxgl as any).accessToken = environment.mapboxKey;
      this.mapa = new Mapboxgl.Map({
        container: 'mapa-mapbox',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [this.lng, this.lat], // LNG, LAT
        zoom: 16.6
    });
    const marker = new Mapboxgl.Marker({
        draggable: false
    }).setLngLat([this.lng, this.lat])
      .addTo(this.mapa);
      var geocoder = new MapboxGeocoder({
          accessToken: (Mapboxgl as any).accessToken,
          mapboxgl: (Mapboxgl as any),
          marker: true,
      });

    this.mapa.addControl(geocoder);
    const markerSearch = new Mapboxgl.Marker({
      draggable: true
  });
  }

  onDragEnd(lng: number, lat: number) {
    const marker = new Mapboxgl.Marker({
        draggable: true
    }).setLngLat([lng, lat])
      .addTo(this.mapa);

    var lngLat = marker.getLngLat();
    console.log(lngLat);
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
          this.data.lng = this.lng;
          this.data.lat = this.lat;
      })

  }


  cargarCoordenadas(){
      this.router.navigateByUrl('donacion-create');
  }

  public volverAlHome(){
    this.router.navigateByUrl('home');
  }

  public calcularNumerito(latitud: number, longitud: number){
    var dLat: number = this.deg2rad(latitud - this.data.userData.latitude);
    var dLon: number = this.deg2rad(longitud - this.data.userData.longitude);
    var temp1: number = Math.pow(Math.sin(dLat / 2), 2.0);
    var temp2: number = Math.cos(this.deg2rad(this.data.userData.latitude)) * Math.cos(this.deg2rad(latitud)) * Math.pow(Math.sin(dLon / 2), 2.0);
    var a: number = temp1 + temp2;
    var aTan: number = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    var res: number = aTan * this.EARTH_RADIUS;
    return Math.floor(res * 100) / 100;
  }

  public deg2rad(dec: number){
    return dec * (Math.PI / 180);
  }

  
}