import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule} from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { DonacionCreateComponent } from './donacion-create/donacion-create.component';
import { ReactiveFormsModule,FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MapComponent } from './map/map.component';
/* modulos para traduccion dinamica*/
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { MainComponent } from './components/main/main.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatPaginatorModule} from '@angular/material/paginator';
import { HeaderComponent } from './header/header.component';
import { FormComponent } from './form/form.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
/*modulos para fecha y moneda*/
//en app.component

export const importsConversor = [
  BrowserModule,
  FormsModule
]


@NgModule({
    declarations: [
        importsConversor,
        AppComponent,
        HomeComponent,
        MainComponent,
        HeaderComponent,
        FormComponent,
        DonacionCreateComponent,
        MapComponent
    ],
    imports: [
      BrowserModule,
      InfiniteScrollModule,
      AppRoutingModule,
      HttpClientModule,
      FormsModule,
      MatPaginatorModule,
      ReactiveFormsModule,
      CommonModule,
      //NgbModule,
        TranslateModule.forRoot(),
        HttpClientModule,
        TranslateModule.forRoot({
              loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [ HttpClient ]
              }
            }),
        BrowserAnimationsModule
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}
  
