package com.ManosALaObra.ManosALaObraBackend.Model;

import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BSUsuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    private String domicilio;
    private String email;
    private String password;
    private Double latitude;
    private Double longitude;
    private Double distancia;


    private Double EARTH_RADIUS = 6371.0;

    @OneToMany(targetEntity = Producto.class)
    @JoinColumn(name="pd2_fk2",referencedColumnName = "id")
    private List<Producto> productos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Producto> getProductos() { return productos; }

    public void setProductos(List<Producto> productos) { this.productos = productos; }

    public Double getLatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getDistancia() { return distancia; }

    public void setDistancia(Double distancia) { this.distancia = distancia; }

    public Usuario(){}

    public Usuario(String nombreUsuario, String domicilio, String email, String password, List<Producto> productos, Double latitude, Double longitude, Double distancia) {
        this.setNombreUsuario(nombreUsuario);
        this.setDomicilio(domicilio);
        this.setEmail(email);
        this.setPassword(password);
        this.setProductos(productos);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setDistancia(distancia);
    }

    public void donarProducto(Producto producto, App app){
        app.agregarDonacion(producto); // Realiza la donación en la app (es el objeto que almacena las donaciones del sistema).
        this.getProductos().add(producto); // Guarda esa donación realizada en una lista de donaciones propia del usuario.
    }

    public void realizarRegistro(Registro registro, App app){
        app.agregarRegistro(registro);
    }

    private Double deg2rad(Double dec) {
        return dec * (Math.PI / 180);
    }

    public Double distance(Double latitude, Double longitude) {

        Double dLat = deg2rad(latitude - this.getLatitude());
        Double dLon = deg2rad( longitude - this.getLongitude());
        Double temp1 = Math.pow(Math.sin(dLat / 2), 2.0);
        Double temp2 = Math.cos(deg2rad(this.getLatitude())) * Math.cos(deg2rad(latitude)) * Math.pow(Math.sin(dLon / 2), 2.0);
        Double a = temp1 + temp2;
        Double aTan = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double res = aTan * EARTH_RADIUS;
        return Math.floor(res * 100) / 100;
    }

}

