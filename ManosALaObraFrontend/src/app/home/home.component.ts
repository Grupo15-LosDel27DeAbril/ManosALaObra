import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { DataService } from '../data.service';
import { Producto } from '../producto';
import { ApiService } from '../api.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Registro } from '../registro';
import { StateComponent } from '../state/state.component';
//import { registerLocaleData } from '@angular/common';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

    selectedDonacion: Producto;
    solicitoDonacion: Boolean = false;
    nombreUsuario: string;
    count: number = 1;
    nombreProducto: string;
    productos: Producto[] = [];
    registros: Registro[] = [];

    constructor(private api: ApiService, private http: HttpClient, public appComp: AppComponent, public data: DataService,private route: Router){

    }

    ngOnInit(){
        this.api.getDonacionesAPI$().subscribe(resp => { this.productos = resp; this.data.productos = resp});
        this.api.getRegistrosAPI$().subscribe(resp => {this.registros = resp; this.data.registros = resp});
        console.log("Save button is clicked!", this.data.productos);
        console.log("El usuario logueado es: ", this.data.userData);
        console.log("Los registros son: " , this.data.registros)
    }

    public ubicacionEnMapa(producto){
        this.data.lat = producto.latitude;
        this.data.lng = producto.longitude;
        this.data.lugar = producto.lugar;
        this.route.navigateByUrl('map');
    }

    public completarFormulario(producto){
        console.log("El email del que hizo esta donacion es: ", producto.emailDonante);
         this.appComp.solicitarLaDonacion(producto.emailDonante);
         this.appComp.agregarMailSolicitante(producto.id);

         //console.log("Save button is clicked!", this.data.productos);
         this.route.navigateByUrl('form');
    }

    public contieneMail(producto: Producto, mail: string){
        var res: Boolean = false;
        for(const data of producto.emailsSolicitantes){
            if(data.mail == mail){
                res = true;
            }
        }
        return res;
    }

    public condition(producto){
        var res: Boolean = false;
        for(const data of producto.emailsSolicitantes){
            res = res || data.mail == this.data.userData.email;
            res = res || producto.estado == "Finalizado";
            res = res || producto.estado == "Entregado";
          }
          return res;
    }

    public esAdministrador(producto){
        return producto.emailDonante == this.data.userData.nombreUsuario;
    }

    public verEstado(producto){
        this.data.emailsActuales = producto.emailsSolicitantes;
        this.route.navigateByUrl('state');
    }
        
}