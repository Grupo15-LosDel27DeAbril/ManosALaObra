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

    public Usuario(){}

    public Usuario(String nombreUsuario, String domicilio, String email, String password, List<Producto> productos) {
        this.setNombreUsuario(nombreUsuario);
        this.setDomicilio(domicilio);
        this.setEmail(email);
        this.setPassword(password);
        this.setProductos(productos);
    }

    public void donarProducto(Producto producto, App app){
        app.agregarDonacion(producto);
        this.getProductos().add(producto);
    }

    public void realizarRegistro(Registro registro, App app){
        app.agregarRegistro(registro);
    }

}
