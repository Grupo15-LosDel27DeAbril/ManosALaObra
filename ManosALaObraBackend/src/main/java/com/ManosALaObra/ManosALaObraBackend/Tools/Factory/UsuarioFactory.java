package com.ManosALaObra.ManosALaObraBackend.Tools.Factory;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioFactory {

    public static Usuario anyUsuario(){
        return new Usuario("sin nombre", "sin domicilio", "sin email", "sin password", new ArrayList<Producto>(), 0.0, 0.0,0.0);
    }

    private static Usuario create(String nombre, String domicilio, String email, String password, List<Producto> productos, Double latitude, Double longitude, Double distancia){
        return new Usuario(nombre,domicilio,email,password, productos, latitude, longitude, distancia);
    }

    public static Usuario createWithNombre(String name){
        return create(name, "sin domicilio", "sin email", "sin password", new ArrayList<Producto>(), 0.0, 0.0, 0.0);
    }

    public static Usuario createWithDomicilio(String domicilio){
        return create("sin nombre", domicilio, "sin email", "sin password", new ArrayList<Producto>(), 0.0, 0.0, 0.0);
    }

    public static Usuario createWithLatitudeYLongitude(Double latitude, Double longitude){
        return create("sin nombre", "sin domicilio", "sin email", "sin password", new ArrayList<Producto>(), latitude, longitude, 0.0);
    }
}