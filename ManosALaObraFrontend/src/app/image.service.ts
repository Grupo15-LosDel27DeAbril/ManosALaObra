import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Options } from 'selenium-webdriver';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class ImageService {
  urlLocal = 'http://localhost:8080/api/'


  constructor(private http: HttpClient) {}


  public uploadImage(image: File): /*Observable<Response>*/Observable<HttpResponse<File>>{ 
    let params = new HttpParams();
    const formData = new FormData();

    formData.append('image', image);

    return this.http.post<File>(this.urlLocal+"/api/v1/image-upload",formData,{observe: 'response'});
    
  }
}