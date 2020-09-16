import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule} from './app-routing.module';
import { AppComponent } from './app.component';
import { AgmCoreModule } from '@agm/core';
import { HomeComponent } from './home/home.component';
import { DonacionCreateComponent } from './donacion-create/donacion-create.component';
import { ReactiveFormsModule,FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
/* modulos para traduccion dinamica*/
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { MainComponent } from './components/main/main.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatPaginatorModule} from '@angular/material/paginator';
import { HeaderComponent } from './header/header.component';
import { FormComponent } from './form/form.component';
import { MapComponent } from './map/map.component';
/*modulos para fecha y moneda*/
//en app.component


@NgModule({
    declarations: [
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
      AgmCoreModule.forRoot({
        apiKey: 'AIzaSyDJRqapKTB9P4DhNX6Tdkx6XruQIOIfEoY',
        libraries: ['places']
      }),
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
  
