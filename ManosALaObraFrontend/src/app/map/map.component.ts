import { Injectable} from '@angular/core';
import { Component } from '@angular/core'; 
import { Producto } from '../producto';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { DataService } from '../data.service';

@Component({
    selector: 'app-root',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.css']
})

@Injectable({
    providedIn: 'root'
})
export class MapComponent {

    title: string = "My first angular-google-maps project";
    lat: number = -34.750148;
    lng: number = -58.245461;
    zoom: number = 16;
    lugar: String = '';
    constructor(private route: Router, public data: DataService){
        this.lat = data.lat;
        this.lng = data.lng;
        this.lugar = data.lugar;
    }

    getPosition(): Promise<any> {

        return new Promise((resolve, reject) => {
            navigator.geolocation.getCurrentPosition(resp => {
                    resolve({lng: resp.coords.longitude, lat: resp.coords.latitude});
                },
                err => {
                    reject(err);
              });
        });
    }

    getLocation() {
        this.getPosition().then(pos => {
            this.lat = pos.lat;
            this.lng = pos.lng;
        });
    } 

    actualizarPosicion(latitude: number, longitude: number){
        this.lat = latitude;
        this.lng = longitude;
    }

    ngOnInit(){
    }

    getMapClick(e) {
        console.log(e.coords.lat, e.coords.lng);
      }

      click(event: google.maps.MouseEvent) {
        console.log(event.latLng)
      }
}