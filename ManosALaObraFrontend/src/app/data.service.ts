import { Injectable } from '@angular/core';
import { Producto } from './producto';
import { UsuarioData } from './usuarioData';
import { App } from './app';

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
    lat: number;
    lng: number;
    lugar: String;

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