package com.ManosALaObra.ManosALaObraBackend.Tools.Builder;

import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;


public class UsuarioBuilder {

    private String nombreUsuario = "";
    private String domicilio = "";
    private String email = "";
    private String password = "";

    public Usuario build() {
        Usuario user = new Usuario(nombreUsuario, domicilio, email, password);
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

}
