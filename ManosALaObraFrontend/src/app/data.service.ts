import { Injectable } from '@angular/core';
import { Producto } from './producto';
import { UsuarioData } from './usuarioData';
import { App } from './app';
import { Registro } from './registro';
import { Mail } from './mail';

@Injectable({
    providedIn: 'root'
})
export class DataService {

    categorias:string[] = ["Alimento","Fruta","Ropa","Juguete"];
    productos: Producto[] = [];
    nombreUsuario: String = "";
    userData: UsuarioData;
    userUpdate: Boolean = false;
    appData: App;
    lng: number;
    lat: number;
    lugar: String;
    registros: Registro[] = [];
    emailsActuales: Mail[];
    productosActualesDeUsuarioLogueado: Producto[];
    emailsActualesEnDonaciones: Mail[];
    productoActual: Producto;
    idProductoActual: number;

    constructor() {}

    setUserUpdate(bool: Boolean){
        this.userUpdate=bool;
    }

    getProductos(): Producto[]{
    /*Busco todo los productos.*/
        return this.productos;
    }

    getuserData(){
        return this.userData;
    }

    getAppData(){
        return this.appData;
    }

    setuserData(user: UsuarioData){
        console.log("Usuario actualizado:",user)
        return this.userData = user; 
    }

    setProductos(prods: Producto[]){
        this.productos = prods; 
    }

    actualizarNombreUsuario(){
        this.nombreUsuario = this.userData.nombreUsuario;
    }
  
}