import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import {FormComponent} from './form/form.component';
import { DonacionCreateComponent } from './donacion-create/donacion-create.component';
import { MapComponent } from './map/map.component';
import { LoginComponent } from './login/login.component';
import { StateComponent } from './state/state.component';
import { MyDonationsComponent } from './my-donations/my-donations.component';
import { EmailDonationsComponent } from './email-donations/email-donations.comonent';
import { ConfirmedRequestComponent } from './confirmed-request/confirmed-request.component';
import { FormularioCreateComponent } from './formulario-create/formulario-create.component';
import { DonationsDeliveredComponent } from './donations-delivered/donations-delivered.component';


const routes: Routes = [
    {path: "", pathMatch: "full", redirectTo: "home"},
    {path: "home", component: HomeComponent},
    {path: "login", component: LoginComponent},
    {path: "form", component: FormComponent},
    {path: "donacion-create", component: DonacionCreateComponent},
    {path: "map", component: MapComponent},
    {path: "state", component: StateComponent},
    {path: "my-donations", component: MyDonationsComponent},
    {path: "email-donations", component: EmailDonationsComponent},
    {path: "confirmed-request", component: ConfirmedRequestComponent},
    {path: "formulario-create", component: FormularioCreateComponent},
    {path: "donations-delivered", component: DonationsDeliveredComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }