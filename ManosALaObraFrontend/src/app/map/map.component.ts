
import { Component } from '@angular/core'; 

@Component({
    selector: 'app-root',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.css']
})
export class MapComponent {

    title: string = "My first angular-google-maps project";
    lat: number = -34.754011;
    lng: number = -58.249344;
    zoom: number = 16;

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

    ngOnInit(){
        this.getLocation()
    }
}