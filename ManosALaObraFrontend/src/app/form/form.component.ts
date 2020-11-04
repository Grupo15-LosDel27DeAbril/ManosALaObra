import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  constructor(public router: Router) { }

  ngOnInit() {
  }

  public volverAlHome(){
    this.router.navigateByUrl('home');
}

}