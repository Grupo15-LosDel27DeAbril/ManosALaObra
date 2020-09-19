package com.ManosALaObra.ManosALaObraBackend.Tools.Builder;

import com.ManosALaObra.ManosALaObraBackend.Model.Geo;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;

public class ProductoBuilder {

    private String nombreProducto = "sin nombre";
    private String categoria = "sin categoria";
    private String descripcion = "sin descripcion";
    private String imagen = "url no asignada";
    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private String lugar = "sin lugar";


    public Producto build() {
        long identificador = new Long(1);
        Producto producto = new Producto(nombreProducto, descripcion, imagen, categoria, identificador, latitude, longitude, lugar);
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

    public ProductoBuilder withLatitude(Double aLatitude){
        latitude = aLatitude;
        return this;
    }

    public ProductoBuilder withLongitude(Double aLongitude){
        longitude = aLongitude;
        return this;
    }

    public ProductoBuilder withLugar(String aPlace){
        lugar = aPlace;
        return this;
    }
}
