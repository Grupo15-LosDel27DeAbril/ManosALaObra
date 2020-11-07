import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { LoginComponent } from './login/login.component';
import { AppRoutingModule} from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { DonacionCreateComponent } from './donacion-create/donacion-create.component';
import { ReactiveFormsModule,FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MapComponent } from './map/map.component';
/*Modulos para inicio de sesion para redes sociales*/
import { SocialLoginModule, AuthServiceConfig } from "angularx-social-login";
import { GoogleLoginProvider, FacebookLoginProvider } from "angularx-social-login";
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
import { ImageUploadComponent } from './image-upload/image-upload.component';
import { from } from 'rxjs';
import { StateComponent } from './state/state.component';
import { MyDonationsComponent } from './my-donations/my-donations.component'
import { EmailDonationsComponent } from './email-donations/email-donations.comonent';
import { ConfirmedRequestComponent } from './confirmed-request/confirmed-request.component';
import { HttpClientComponent } from './http-client/http-client.component';

let config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider("311052136099-qqf6f12v6pdpt5tfv67lqfe8gdivg920.apps.googleusercontent.com")
  },
]);

export function provideConfig() {
  return config;
}

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        HomeComponent,
        MainComponent,
        HeaderComponent,
        FormComponent,
        ConfirmedRequestComponent,
        StateComponent,
        MyDonationsComponent,
        EmailDonationsComponent,
        DonacionCreateComponent,
        MapComponent,
        ImageUploadComponent,
        HttpClientComponent
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
      SocialLoginModule,
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
    providers: [ {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    }],
    bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}
  
