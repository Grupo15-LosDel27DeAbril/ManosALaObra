import { Component, OnInit} from '@angular/core';
import { AppComponent } from '../app.component';
import { Producto } from '../producto';
import { Router, RouterLink, NavigationEnd } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../data.service';
import { Injectable} from '@angular/core';
import * as Mapboxgl from 'mapbox-gl';
import { environment } from '../../enviroments/environment';
import * as Scroll from 'ngx-infinite-scroll';
import { FileUploader } from 'ng2-file-upload';

const uploadAPI = 'http://localhost:4200/upload'

@Component({
    selector: 'app-donacion-create',
    templateUrl: './donacion-create.component.html',
    styleUrls: ['./donacion-create.component.css']
})

@Injectable({
    providedIn: 'root'
})
export class DonacionCreateComponent implements OnInit{
    data: [][];
    producto: Producto;
    alert: Boolean = false;
    productoForm: FormGroup;
    mapa: Mapboxgl.Map;
    lng: number;
    lat: number;
    uploader: FileUploader;

    constructor(public router: Router, public appComp: AppComponent, public dataService: DataService){
        this.productoForm = this.createFormGroup();
        this.uploader= new FileUploader({ url: uploadAPI, itemAlias: 'file' });
    }


    ngOnInit() {
        this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
        this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
          console.log('FileUpload:uploaded successfully:', item, status, response);
          alert('Your file has been uploaded successfully');
        };
        (Mapboxgl as any).accessToken = environment.mapboxKey;
        this.mapa = new Mapboxgl.Map({
          container: 'mapa-mapbox',
          style: 'mapbox://styles/mapbox/streets-v11',
          center: [-58.2535363, -34.7405353], // LNG, LAT
          zoom: 16.6
    });
    this.crearMarcador(-58.2535363, -34.7405353);
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
              this.dataService.lng = this.lng;
              this.dataService.lat = this.lat;
              this.productoForm.get('longitude').setValue(this.lng);
              this.productoForm.get('latitude').setValue(this.lat);
          })
    
      }


    public establecerUbicacion(){
        this.router.navigateByUrl('map');
    }

     onScrollDown() {
        console.log('scrolled down!!');
      }
     
      onScrollUp() {
        console.log('scrolled up!!');
      }

    createFormGroup(){
        return new FormGroup({
            nombreProducto: new FormControl('',[Validators.required,Validators.minLength(2)]),
            categoria: new FormControl('',[Validators.required, Validators.minLength(2)]),
            descripcion: new FormControl('',[Validators.required, Validators.minLength(2)]),
            longitude: new FormControl('',[Validators.required]),
            latitude: new FormControl('',[Validators.required]),
            lugar: new FormControl('',[Validators.required, Validators.minLength(2)]),
            fechaPublicacion: new FormControl('',[Validators.required, Validators.minLength(10), Validators.maxLength(10)]),
            validoHasta: new FormControl('',[Validators.required, Validators.minLength(10), Validators.maxLength(10)]) 
        });
    }
    onResetForm(){
        this.productoForm.reset();
    }

    onSaveForm(){
        if(this.productoForm.valid){
            this.appComp.donarProducto(this.productoForm.value);
            this.alert = true;
            this.onResetForm();
            console.log('valid');
        }else{
            console.log('not valid');
        }
    }

    get nombreProducto(){
        return this.productoForm.get('nombreProducto');
    }
    get categoria(){
        return this.productoForm.get('categoria');
    }
    get descripcion(){
        return this.productoForm.get('descripcion');
    }
    get imagen(){
        return this.productoForm.get('imagen');
    }
    get longitude(){
        return this.productoForm.get('longitude');
    }
    get latitude(){
        return this.productoForm.get('latitude');
    }
    get lugar(){
        return this.productoForm.get('lugar');
    }

    get fechaPublicacion(){
        return this.productoForm.get('fechaPublicacion');
    }

    get validoHasta(){
        return this.productoForm.get('validoHasta');
    }

    public volverAlHome(){
        this.router.navigateByUrl('home');
    }
}