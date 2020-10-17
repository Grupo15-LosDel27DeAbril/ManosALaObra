package com.ManosALaObra.ManosALaObraBackend.Model;
import java.awt.*;
import java.io.File;
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class


import javax.persistence.*;
import org.apache.log4j.Logger;
import org.hibernate.type.ImageType;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private File imagen;
    private Double latitude;
    private Double longitude;
    private String lugar;
    private LocalDate fechaPublicacion;
    private LocalDate validoHasta;
    private String emailDonante;


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

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
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

    public LocalDate getFechaPublicacion() { return fechaPublicacion; }

    public void setFechaPublicacion(LocalDate fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public LocalDate getValidoHasta() { return validoHasta; }

    public void setValidoHasta(LocalDate validoHasta) { this.validoHasta = validoHasta; }

    public String getEmailDonante() {
        return emailDonante;
    }

    public void setEmailDonante(String correoelectronico){
        this.emailDonante = correoelectronico;
    }

    public String imprimirFecha(LocalDate unaFecha){
        LocalDate dateObj = unaFecha;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = dateObj.format(myFormatObj);
       return formattedDate;
    }

    public Producto() {
        this.fechaPublicacion = LocalDate.now();
    }

    public Producto(String nombre){
        this.setNombreProducto(nombre);
        this.fechaPublicacion = LocalDate.now();
    }

    public Producto(String nombreProducto, String descripcion, File imagen, String categoria, Double latitude, Double longitude, String lugar, LocalDate desde, LocalDate hasta) {
        this.setNombreProducto(nombreProducto);
        this.setCategoria(categoria);
        this.setDescripcion(descripcion);
        this.setImagen(imagen);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setLugar(lugar);
        this.setFechaPublicacion(desde);
        this.setValidoHasta(hasta);
    }

    public Producto(String nombreProducto, String descripcion, File imagen, String categoria, long id, Double latitude, Double longitude, String lugar, LocalDate desde, LocalDate hasta) {
        this.setNombreProducto(nombreProducto);
        this.setCategoria(categoria);
        this.setDescripcion(descripcion);
        this.setImagen(imagen);
        this.setId(id);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setLugar(lugar);
        this.setFechaPublicacion(desde);
        this.setValidoHasta(hasta);
    }

    public void imprimirEnPantalla() {
        Logger log = Logger.getLogger(this.getClass());
        log.trace("[");
        log.trace("Nombre del producto" + this.getNombreProducto());
        log.trace(" , Categoria" + this.getCategoria());
        log.trace(" , Descripcion" + this.getDescripcion());
        log.trace(" ,disponible desde: "+ this.imprimirFecha(this.fechaPublicacion));
        log.trace("]");
    }
}
