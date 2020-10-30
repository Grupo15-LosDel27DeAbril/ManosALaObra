import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { observable, Observable } from 'rxjs';
import { Producto } from './producto';
import { UsuarioData } from './usuarioData';
import { App } from './app';
import { Registro } from './registro';
//import { observeOn } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class ApiService {
    urlLocal = 'http://localhost:8080/api/'

    constructor(private http: HttpClient) { }

    loginWithGoogle(user: any): Observable<any>{
    /* Se hace el llamado al backend para loguearse con google */
       return this.http.post(this.urlLocal+'/usuario/loginWithGoogle', user);
    }

    getDonacionesAPI$(): Observable<Producto[]> {
        /* Busco todas las donaciones cargadas en el sistema */
        return this.http.get<Producto[]>(
            this.urlLocal+'donaciones');
    }

    getUserData$(idUser: string): Observable<HttpResponse<UsuarioData>> {
        /* Se busca los datos del usuario. */
        return this.http.get<UsuarioData>(this.urlLocal+'usuario/'+idUser, {observe: 'response'});
    }

    donarProductoAApi(producto: Producto, idUser:any, idApp: any): Observable<HttpResponse<UsuarioData>>{
        return this.http.post<UsuarioData>(this.urlLocal+"donarProducto/"+idUser+"/"+idApp, producto, {observe: 'response'})
    } 

    realizarSolicitudDeDonacion(userData: UsuarioData, emailDonante: string){
        return this.http.put<UsuarioData>(this.urlLocal+'/usuario/solicitarDonacion/'+userData.id,emailDonante,{observe:'response'});
    }

    realizarSumatoriaDeMail(userData: UsuarioData, id: number, idApp: any): Observable<UsuarioData>{
        return this.http.put<UsuarioData>(this.urlLocal+'agregarMail/'+userData.id+"/"+id+"/"+idApp,{observe:'response'});
    }

    getRegistrosAPI$(): Observable<Registro[]>{
        /* Busco todos los registros cargados en el sistema. */
        return this.http.get<Registro[]>(this.urlLocal+'registros');
    }

    agregarRegistroAApi(registro: Registro, idUser: any, idApp: any): Observable<HttpResponse<UsuarioData>>{
        return this.http.post<UsuarioData>(this.urlLocal+"agregarRegistro/"+idUser+"/"+idApp, registro ,{observe:'response'});
    }
    
}