import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from './producto';
import { UsuarioData } from './usuarioData';
import { App } from './app';

@Injectable({
    providedIn: 'root'
})
export class ApiService {
    urlLocal = 'http://localhost:8080/api/'

    constructor(private http: HttpClient) { }

    getDonacionesAPI$(): Observable<Producto[]> {
        /* Busco todas las donaciones cargadas en el sistema */
        return this.http.get<Producto[]>(
            this.urlLocal+'donaciones');
    }

    donarProductoAApi(producto: Producto, idUser:any, idApp: any): Observable<HttpResponse<UsuarioData>>{
        return this.http.post<UsuarioData>(this.urlLocal+"donarProducto/"+idUser+"/"+idApp, producto, {observe: 'response'})
    } 
    
}