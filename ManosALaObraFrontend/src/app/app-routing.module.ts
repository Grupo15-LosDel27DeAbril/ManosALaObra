import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import {FormComponent} from './form/form.component';
import { DonacionCreateComponent } from './donacion-create/donacion-create.component';
import { MapComponent } from './map/map.component';

const routes: Routes = [
    {path: "", pathMatch: "full", redirectTo: "home"},
    {path: "home", component: HomeComponent},
    {path: "form", component: FormComponent},
    {path: "donacion-create", component: DonacionCreateComponent},
    {path: "map", component: MapComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }