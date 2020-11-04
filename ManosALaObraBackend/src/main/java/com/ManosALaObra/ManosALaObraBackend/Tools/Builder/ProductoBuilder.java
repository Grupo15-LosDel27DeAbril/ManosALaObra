package com.ManosALaObra.ManosALaObraBackend.Tools.Builder;

import com.ManosALaObra.ManosALaObraBackend.Model.Mail;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductoBuilder {

    private String nombreProducto = "sin nombre";
    private String categoria = "sin categoria";
    private String descripcion = "sin descripcion";
    private String imagen = "url no asignada";
    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private String lugar = "sin lugar";
    private LocalDate fechaPublicacion = LocalDate.of(2020, 10, 13);
    private LocalDate fechaLimite = LocalDate.of(2020, 10, 20);
    private String emailDonante = "sin email";
    private List<Mail> emailsSolicitantes = new ArrayList<Mail>();
    private String estado = "sin estado";



    public Producto build() {
        long identificador = new Long(1);
        Producto producto = new Producto(nombreProducto, descripcion, imagen, categoria, identificador, latitude, longitude, lugar, fechaPublicacion, fechaLimite, emailDonante, emailsSolicitantes, estado);
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

    public ProductoBuilder withFechaPublicacion(LocalDate aDate){
        fechaPublicacion = aDate;
        return this;
    }

    public ProductoBuilder withFechaLimite(LocalDate aDate){
        fechaLimite = aDate;
        return this;
    }

    public ProductoBuilder withEmailDonante(String aEmail){
        emailDonante = aEmail;
        return this;
    }

    public ProductoBuilder withEmailsSolicitantes(List<Mail> anEmails){
        emailsSolicitantes = anEmails;
        return this;
    }

    public ProductoBuilder withEstado(String anState){
        estado = anState;
        return this;
    }
}
