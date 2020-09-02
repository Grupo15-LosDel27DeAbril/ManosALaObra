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

    public Producto(String nombreProducto, String descripcion, String imagen, String categoria) {
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Producto(String nombreProducto, String descripcion, String imagen, String categoria, Long id) {
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.id = id;
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
