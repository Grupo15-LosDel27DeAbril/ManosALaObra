import { Component, OnInit} from '@angular/core';
import { AppComponent } from '../app.component';
import { Mail } from '../mail';
import { Router, RouterLink, NavigationEnd } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../data.service';
import { ApiService } from '../api.service';
import { Injectable} from '@angular/core';

@Component({
    selector: 'app-formulario-create',
    templateUrl: './formulario-create.component.html',
    styleUrls: ['./formulario-create.component.css']
})

@Injectable({
    providedIn: 'root'
})
export class FormularioCreateComponent implements OnInit{
    alert: Boolean = false;
    mailForm: FormGroup;


    constructor(public router: Router, public appComp: AppComponent, public dataService: DataService, public api: ApiService){
        this.mailForm = this.createFormGroup();
    }

    ngOnInit(){
        //this.mailForm.get('mail').setValue(this.dataService.userData.email);
    }

    createFormGroup(){
        return new FormGroup({
            name: new FormControl('', [Validators.required,Validators.minLength(3)]),
            motivo: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(30)])
        });
    }

    onResetForm(){
        this.mailForm.reset();
    }

    onSaveForm(){
        if(this.mailForm.valid){
            this.appComp.agregarMailConMotivo(this.mailForm.value, this.dataService.idProductoActual, this.dataService.userData.id);
            this.alert = true;
            this.onResetForm();
            console.log('valid man');
        }else{
            console.log('not valid man');
        }
    }


    get name(){
        return this.mailForm.get('name');
    }

    get motivo(){
        return this.mailForm.get('motivo');
    }

    confirmado(){
        this.router.navigateByUrl('form');
    }

    public volverAlHome(){
        this.router.navigateByUrl('home');
    }
}
