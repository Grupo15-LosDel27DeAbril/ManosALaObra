package com.ManosALaObra.ManosALaObraBackend.Tools.Builder;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;

import java.util.ArrayList;
import java.util.List;


public class UsuarioBuilder {

    private String nombreUsuario = "";
    private String domicilio = "";
    private String email = "";
    private String password = "";
    private List<Producto> productos = new ArrayList<Producto>();

    public Usuario build() {
        Usuario user = new Usuario(nombreUsuario, domicilio, email, password, productos);
        return user;
    }

    public UsuarioBuilder withNombreUsuario(String name){
        this.nombreUsuario = name;
        return this;
    }

    public UsuarioBuilder withDomicilio(String domicilio){
        this.domicilio = domicilio;
        return this;
    }

    public UsuarioBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public UsuarioBuilder withPassword(String password){
        this.password = password;
        return this;
    }

    public UsuarioBuilder withProductos(final List<Producto> aList){
        this.productos = aList;
        return this;
    }

}
