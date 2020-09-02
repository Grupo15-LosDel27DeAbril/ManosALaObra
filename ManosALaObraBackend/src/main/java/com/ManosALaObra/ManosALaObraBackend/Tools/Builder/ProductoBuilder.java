package com.ManosALaObra.ManosALaObraBackend.Tools.Builder;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;

public class ProductoBuilder {

    private String nombreProducto = "sin nombre";
    private String categoria = "sin categoria";
    private String descripcion = "sin descripcion";
    private String imagen = "url no asignada";


    public Producto build() {
        long identificador = new Long(1);
        Producto producto = new Producto(nombreProducto, descripcion, imagen, categoria, identificador);
        return producto;
    }

    public ProductoBuilder withNombreProducto(String aValue){
        nombreProducto = aValue;
        return this;
    }

    public ProductoBuilder withDescripcion(String aValue){
        descripcion = aValue;
        return this;
    }

    public ProductoBuilder withImagen(String aValue){
        imagen = aValue;
        return this;
    }

    public ProductoBuilder withCategoria(String aValue){
        categoria = aValue;
        return this;
    }
}
