import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpEventType } from '@angular/common/http';
import { DataService } from '../data.service';
import { toBase64String } from '@angular/compiler/src/output/source_map';


@Component({
  selector: 'app-http-client',
  templateUrl: './http-client.component.html',
  styleUrls: ['./http-client.component.css']
})
export class HttpClientComponent implements OnInit {
  fileData: File;
  previewUrl: any;
  fileUploadProgress: string;
  uploadedFilePath: string;


  constructor(private http: HttpClient, public data: DataService) { }
  ngOnInit(): void {
    
  }

   
  fileProgress(fileInput: any) {
        this.fileData = <File>fileInput.target.files[0];
        this.preview();
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
        this.data.imagenActual = this.previewUrl;
      }
  }
  
  /*
  onSubmit() {
      const formData = new FormData();
        formData.append('file', this.fileData);
        this.http.post('localhost:8080/app/fileUpload', formData)
          .subscribe(res => {
            console.log(res);
            this.uploadedFilePath = res.data.filePath;//aqui me guardo la direccion de la imagen
            alert('SUCCESS !!');
          })
  }*/

  onSubmit() {
    const formData = new FormData();
    formData.append('files', this.fileData);
     
    this.fileUploadProgress = '0%';
 
    this.http.post('http://localhost:8080/api/fileUpload', formData, {
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
 } 

}