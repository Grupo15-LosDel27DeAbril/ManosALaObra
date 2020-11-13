import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpEventType } from '@angular/common/http';


@Component({
  selector: 'app-http-client',
  templateUrl: './http-client.component.html',
  styleUrls: ['./http-client.component.css']
})
export class HttpClientComponent implements OnInit {
  fileData: File;
  previewUrl: any = null;
  fileUploadProgress: string = null;
  uploadedFilePath: string = null;


  constructor(private http: HttpClient) { }

  ngOnInit() {
  }
  preview() {
    // Show preview 
    var mimeType = this.fileData.type;
    if (mimeType.match(/image\/*/) == null) {
      return;
    }

    var reader = new FileReader();
    reader.readAsDataURL(this.fileData);
    reader.onload = (_event) => {
      this.previewUrl = reader.result;
    }
  }

  onSubmit() {
    var urlLocal = 'http://localhost:8080/api/';
    this.fileUploadProgress = '0%';


    var reader = new FileReader();
    const formData = new FormData();
    formData.append('file', this.fileData);
    this.http.post(urlLocal + 'fileUpload', formData, {
      reportProgress: true,
      observe: 'events'
    })
      .subscribe(res => {
        console.log(res);
        reader.readAsDataURL(this.fileData);
        this.uploadedFilePath = reader.result.toString();
        //this.uploadedFilePath = res.data.filePath;//direccion que quiero capturar
        alert('SUCCESS !!');
      })

    /*  
   onSubmit() {
     const formData = new FormData();
     formData.append('files', this.fileData);
     var urlLocal = 'http://localhost:8080/api/';
    
     this.fileUploadProgress = '0%';
   
     this.http.post(urlLocal+'fileUpload', formData, {
       reportProgress: true,
       observe: 'events'   
     })
     .subscribe(events => {
       if(events.type === HttpEventType.UploadProgress) {
         this.fileUploadProgress = Math.round(events.loaded / events.total * 100) + '%';
         console.log(this.fileUploadProgress);
       } else if(events.type === HttpEventType.Response) {
         this.fileUploadProgress = ''; 
         console.log(events.body);          
         alert('SUCCESS !!');
       }
          
     }) 
     */
   }
   
  }
