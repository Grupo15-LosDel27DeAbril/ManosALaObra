package com.ManosALaObra.ManosALaObraBackend.Tools.Factory;

import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;

public class UsuarioFactory {

    public static Usuario anyUsuario(){
        return new Usuario("sin nombre", "sin domicilio", "sin email", "sin password");
    }

    private static Usuario create(String nombre, String domicilio, String email, String password){
        return new Usuario(nombre,domicilio,email,password);
    }

    public static Usuario createWithNombre(String name){
        return create(name, "sin domicilio", "sin email", "sin password");
    }

    public static Usuario createWithDomicilio(String domicilio){
        return create("sin nombre", domicilio, "sin email", "sin password");
    }
}