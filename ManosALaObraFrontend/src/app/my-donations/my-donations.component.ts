import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { UsuarioData } from '../usuarioData';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AppComponent } from '../app.component';
import { DataService } from '../data.service';


@Component({
    selector: 'app-my-donations',
    templateUrl: './my-donations.component.html',
    styleUrls: ['./my-donations.component.css']
})

export class MyDonationsComponent implements OnInit{

    constructor(public appComp: AppComponent, private route: Router, public data: DataService){}

    ngOnInit(){

    }

    verSolicitantes(producto){
        this.data.productoActual = producto;
        this.data.emailsActualesEnDonaciones = producto.emailsSolicitantes;
        this.route.navigateByUrl('email-donations');
        
    }

    public volverAlHome(){
        this.route.navigateByUrl('home');
    }

    public fueDonado(producto){
        return producto.fueDonado;
    }

    public cambiarAEstadoEntregado(producto){
        this.appComp.cambiarEstadoEntregado(producto.id);
    }

    public noEsteEntregado(producto){
        var res: Boolean = (producto.estado == "Finalizado" || producto.estado == "Entregado");
        return res; 
    }
}