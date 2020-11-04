package com.ManosALaObra.ManosALaObraBackend.Tools.Factory;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioFactory {

    public static Usuario anyUsuario(){
        return new Usuario("sin nombre", "sin domicilio", "sin email", "sin password", new ArrayList<Producto>());
    }

    private static Usuario create(String nombre, String domicilio, String email, String password, List<Producto> productos){
        return new Usuario(nombre,domicilio,email,password, productos);
    }

    public static Usuario createWithNombre(String name){
        return create(name, "sin domicilio", "sin email", "sin password", new ArrayList<Producto>());
    }

    public static Usuario createWithDomicilio(String domicilio){
        return create("sin nombre", domicilio, "sin email", "sin password", new ArrayList<Producto>());
    }
}