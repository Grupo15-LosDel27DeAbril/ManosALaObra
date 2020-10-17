import { ɵLocaleDataIndex } from '@angular/core';
import { DatePipe } from '@angular/common'
import { FileUploader } from 'ng2-file-upload';

export interface Producto{
    id: number,
    nombreProducto: string,
    categoria: string,
    descripcion: string,
    imagen: FileUploader,
    latitude: number,
    longitude: number,
    lugar: string,
    email: string,
    fechaPublicacion: DatePipe;
    validoHasta: DatePipe
}
