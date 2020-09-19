package com.ManosALaObra.ManosALaObraBackend.Model;

import javax.persistence.*;
import org.apache.log4j.Logger;

@Entity
@Table(name = "BSProducto")
public class Producto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre")
    private String nombreProducto;
    private String categoria;
    private String descripcion;
    private String imagen;
    private Double latitude;
    private Double longitude;
    private String lugar;

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Producto() {}

    public Producto(String nombre){
        this.setNombreProducto(nombre);
    }

    public Producto(String nombreProducto, String descripcion, String imagen, String categoria, Double latitude, Double longitude, String lugar) {
        this.setNombreProducto(nombreProducto);
        this.setCategoria(categoria);
        this.setDescripcion(descripcion);
        this.setImagen(imagen);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setLugar(lugar);
    }

    public Producto(String nombreProducto, String descripcion, String imagen, String categoria, Long id, Double latitude, Double longitude, String lugar) {
        this.setNombreProducto(nombreProducto);
        this.setCategoria(categoria);
        this.setDescripcion(descripcion);
        this.setImagen(imagen);
        this.setId(id);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setLugar(lugar);
    }

    public void imprimirEnPantalla() {
        Logger log = Logger.getLogger(this.getClass());
        log.trace("[");
        log.trace("Nombre del producto" + this.getNombreProducto());
        log.trace(" , Categoria" + this.getCategoria());
        log.trace(" , Descripcion" + this.getDescripcion());
        log.trace("]");
    }



}
