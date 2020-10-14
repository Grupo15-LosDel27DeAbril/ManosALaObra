package com.ManosALaObra.ManosALaObraBackend.Tools.Factory;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;

import java.time.LocalDate;

public class ProductoFactory {

    public static Producto anyProducto() {
        String nombreProducto = "sin nombre";
        String categoria = "sin categoria";
        String descripcion = "sin descripcion";
        String imagen = "url no disponible";
        Double latitude = 0.0;
        Double longitude = 0.0;
        String lugar = "sin lugar";
        long id = 0;
        LocalDate fechaPublicacion = LocalDate.of(2020, 10, 13);
        LocalDate fechaLimite = LocalDate.of(2020, 10, 20);
        return new Producto(nombreProducto, descripcion, imagen, categoria, id, latitude, longitude, lugar, fechaPublicacion, fechaLimite);
    }

    private static Producto create(String nombre, String descripcion, String imagen, String categoria, long id, Double latitude, Double longitude, String lugar, LocalDate desde, LocalDate hasta){
        return new Producto(nombre, descripcion, imagen, categoria, id, latitude, longitude, lugar, desde, hasta);
    }

    public static Producto createWithNombre(String nombre){
        return create(nombre, "sin descripcion", "url no disponible", "sin categoria", 1, 0.0, 0.0, "sin lugar", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 16));
    }

    public static Producto createWithNombreAndDescripcion(String nombre, String descripcion){
        return create(nombre, descripcion, "url no disponible", "sin categoria", 2, 0.0, 0.0, "sin lugar", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 16));
    }

    public static Producto createWithCategoria(String categoria){
        return create("sin nombre", "sin descripcion", "url no disponible", categoria, 3, 0.0, 0.0, "sin lugar", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 16));
    }

    public static Producto createWithId(long id){
        return create("sin nombre", "sin descripcion", "url no disponible", "sin categoria", 4, 0.0, 0.0, "sin lugar", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 16));
    }

    public static Producto createWithDescripcion(String descripcion){
        return create("sin nombre", descripcion, "url no disponible", "sin categoria", 5, 0.0, 0.0, "sin lugar", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 16));
    }

    public static Producto createWithNombreDescripcionYCategoria(String nombre, String descripcion, String categoria){
        return create(nombre, descripcion, "url no disponible", categoria, 6, 0.0, 0.0, "sin lugar", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 16));
    }
}
