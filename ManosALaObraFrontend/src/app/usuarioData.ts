import { Producto } from './producto';

export interface UsuarioData {
    id: number,
    nombreUsuario: string,
    domicilio: string,
    email: string,
    password: string,
    productos: Producto[],
    latitude: number,
    longitude: number,
    distancia: number,
} 