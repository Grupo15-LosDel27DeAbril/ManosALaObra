import { Component, OnInit} from '@angular/core';
import { AppComponent } from '../app.component';
import { Producto } from '../producto';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-donacion-create',
    templateUrl: './donacion-create.component.html',
    styleUrls: ['./donacion-create.component.css']
})
export class DonacionCreateComponent implements OnInit{
    data: [][];
    producto: Producto;
    alert: Boolean = false;
    productoForm: FormGroup;

    constructor(public router: Router, public appComp: AppComponent){
        this.productoForm = this.createFormGroup();
    }

    ngOnInit() {
    }

    createFormGroup(){
        return new FormGroup({
            nombreProducto: new FormControl('',[Validators.required,Validators.minLength(2)]),
            categoria: new FormControl('',[Validators.required, Validators.minLength(2)]),
            descripcion: new FormControl('',[Validators.required, Validators.minLength(2)]),
            imagen: new FormControl('',[Validators.required, Validators.minLength(5)]),
            latitude: new FormControl('',[Validators.required]),
            longitude: new FormControl('',[Validators.required]),
            lugar: new FormControl('',[Validators.required, Validators.minLength(2)])
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
    get latitude(){
        return this.productoForm.get('latitude');
    }
    get longitude(){
        return this.productoForm.get('longitude');
    }
    get lugar(){
        return this.productoForm.get('lugar');
    }

    public volverAlHome(){
        this.router.navigateByUrl('home');
    }
}