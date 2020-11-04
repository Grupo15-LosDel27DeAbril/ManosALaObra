import { ɵLocaleDataIndex } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Mail } from './mail';

export interface Producto{
    id: number,
    nombreProducto: string,
    categoria: string,
    descripcion: string,
    imagen: string,
    latitude: number,
    longitude: number,
    lugar: string,
    fechaPublicacion: DatePipe,
    validoHasta: DatePipe,
    emailDonante: string,
    estado: string,
    idDonante: number,
    emailsSolicitantes: Mail[],
    fueDonado: boolean,
}
