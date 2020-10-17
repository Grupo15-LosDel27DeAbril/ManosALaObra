import { Component } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ApiService } from './api.service';
import { Observable, throwError } from 'rxjs';
import { Producto } from './producto';
import { DataService } from './data.service';
import { Router, RouterLink } from '@angular/router';
import { UsuarioData } from './usuarioData';

import { retry, catchError } from 'rxjs/operators';
/*para la traduccion*/
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { Title } from '@angular/platform-browser';


@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'ManosALaObraFrontend';
   donaciones: Producto[] = [];
   
   constructor(public router: Router, private http: HttpClient, private api: ApiService, public data: DataService, private translate: TranslateService, private titleService: Title ){
    this.translate.setDefaultLang('es');
    this.translate.use('es');

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.translate.get('app.title').subscribe((res: string) => {
        this.titleService.setTitle(res);
      });
    });
   }

   public getProductos(): Array<{nombreProducto,categoria,descripcion,imagen}>{
       return this.data.productos
   }

   handleError(error) {
    let errorMessage = '';
     errorMessage = `Error: ${error.error.message}`;
    window.alert(errorMessage);
  }

  public donarProducto(product: Producto){
    this.api.donarProductoAApi(product, this.data.getuserData().id, 1) 
        .subscribe(resp => { const data = resp.body
                             this.data.setuserData(data);
                           },
                           err => {
                             console.log(err);
                             this.handleError(err);
                           });
   }

   public getUserData(idUser: string){
     /* Se consulta a la API y se obtiene los datos del usuario */
     this.api.getUserData$(idUser)
         .subscribe(resp => {
                        const data = resp.body
                        this.data.userData = data;
                        this.data.nombreUsuario = this.data.userData.nombreUsuario;
                        this.router.navigateByUrl('/home');
         },
         err => console.log(err));
   }

   public gestionarLogin(user: any){
     /*Esta función se encarga de usar la api para loguearme , en este caso con Google*/
     this.api.loginWithGoogle(user)
         .subscribe(resp => { const data = resp
                              /* Se llena el UserData con todos los datos del usuario. */
                              this.getUserData(data.id.toString()); 
                            },
                    err => console.log(err));
   }
   
}

