package com.ManosALaObra.ManosALaObraBackend.Model;

import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;

import javax.persistence.*;

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

    public Usuario(String nombreUsuario, String domicilio, String email, String password) {
        this.nombreUsuario = nombreUsuario;
        this.domicilio = domicilio;
        this.email = email;
        this.password = password;
    }

    public void donarProducto(Producto producto, App app){
        app.agregarDonacion(producto);
    }
}
