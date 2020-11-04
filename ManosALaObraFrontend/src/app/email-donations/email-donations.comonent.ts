import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Producto } from '../producto';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AppComponent } from '../app.component';
import { DataService } from '../data.service';

@Component({
    selector: 'app-email-donations',
    templateUrl: './email-donations.component.html',
    styleUrls: ['./email-donations.component.css']
})

export class EmailDonationsComponent implements OnInit{
     
    constructor(public appComp: AppComponent, private route: Router, public data: DataService){}

    ngOnInit(){
        
    }

    volverAtras(){
        this.route.navigateByUrl('my-donations');
    }

    enviarMailConfirmacion(email){
        //this.appComp.confirmarLaDonacion(email.mail, this.data.productoActual);
        this.appComp.cambiarEstado(this.data.productoActual.id);
        this.appComp.eliminarDonacion(this.data.userData.id, this.data.productoActual.id);
        this.route.navigateByUrl('confirmed-request');
    }
}