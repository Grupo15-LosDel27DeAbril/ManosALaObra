package com.ManosALaObra.ManosALaObraBackend.Model;
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class


import javax.persistence.*;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    private String fechaPublicacion;
    private String validoHasta;
    private String emailDonante;
    private String estado;
    private long idDonante;

    @OneToMany(targetEntity = Mail.class)
    @JoinColumn(name="ms_fk",referencedColumnName = "id")
    private List<Mail> emailsSolicitantes;   // Se tuvo que crear objetos de tipo "Mail" porque con lista de strings no permitía un mapeo óptimo en hibernate.
    private boolean fueDonado;

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

    public String getFechaPublicacion() { return fechaPublicacion; }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String text = fechaPublicacion.format(formatter);
        this.fechaPublicacion = text;
    }

    public String getValidoHasta() { return validoHasta; }

    public void setValidoHasta(LocalDate validoHasta) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String text = validoHasta.format(formatter);
        this.validoHasta = text;
    }

    public String getEmailDonante() {
        return emailDonante;
    }

    public void setEmailDonante(String correoelectronico){
        this.emailDonante = correoelectronico;
    }

    public List<Mail> getEmailsSolicitantes() { return emailsSolicitantes; }

    public void setEmailsSolicitantes(List<Mail> emailsSolicitantes) { this.emailsSolicitantes = emailsSolicitantes; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public long getIdDonante() { return idDonante; }

    public void setIdDonante(long idDonante) { this.idDonante = idDonante; }

    public boolean isFueDonado() { return fueDonado; }

    public void setFueDonado(boolean fueDonado) { this.fueDonado = fueDonado; }

    public String imprimirFecha(LocalDate unaFecha){
        LocalDate dateObj = unaFecha;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dateObj.format(myFormatObj);
       return formattedDate;
    }

    public Producto() {
        this.fechaPublicacion = "01-01-2021";
    }

    public Producto(String nombre){
        this.setNombreProducto(nombre);
        this.fechaPublicacion = "01-01-2021";
    }

    public Producto(String nombreProducto, String descripcion, String imagen, String categoria, Double latitude, Double longitude, String lugar, LocalDate desde, LocalDate hasta, String emailDonante, List<Mail> emailsSolicitantes, String estado, long idDonante, boolean fueDonado){
        this.setNombreProducto(nombreProducto);
        this.setCategoria(categoria);
        this.setDescripcion(descripcion);
        this.setImagen(imagen);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setLugar(lugar);
        this.setFechaPublicacion(desde);
        this.setValidoHasta(hasta);
        this.setEmailDonante(emailDonante);
        this.setEmailsSolicitantes(emailsSolicitantes);
        this.setEstado(estado);
        this.setIdDonante(idDonante);
        this.setFueDonado(fueDonado);
    }

    public Producto(String nombreProducto, String descripcion, String imagen, String categoria, long id, Double latitude, Double longitude, String lugar, LocalDate desde, LocalDate hasta, String emailDonante, List<Mail> emailsSolicitantes, String estado, long idDonate, boolean fueDonado) {
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
        this.setEmailDonante(emailDonante);
        this.setEmailsSolicitantes(emailsSolicitantes);
        this.setEstado(estado);
        this.setIdDonante(idDonate);
        this.setFueDonado(fueDonado);
    }

    public void agregarMail(Mail mail){
        this.getEmailsSolicitantes().add(mail);
    }

    public void imprimirEnPantalla() {
        Logger log = Logger.getLogger(this.getClass());
        log.trace("[");
        log.trace("Nombre del producto" + this.getNombreProducto());
        log.trace(" , Categoria" + this.getCategoria());
        log.trace(" , Descripcion" + this.getDescripcion());
        //log.trace(" ,disponible desde: "+ this.imprimirFecha(this.fechaPublicacion));
        log.trace("]");
    }
}
