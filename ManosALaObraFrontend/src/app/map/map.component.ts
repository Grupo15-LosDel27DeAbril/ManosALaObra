import { Injectable} from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { environment } from '../../enviroments/environment';
import { DataService } from '../data.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import * as Mapboxgl from 'mapbox-gl';

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

    constructor(public data: DataService, public router: Router){
        this.lng = data.lng;
        this.lat = data.lat;
        this.lugar = data.lugar;
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

  
}