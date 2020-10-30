package com.ManosALaObra.ManosALaObraBackend.Tools.Builder;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Model.Registro;

import java.util.ArrayList;
import java.util.List;

public class AppBuilder {

    private List<Producto> productos = new ArrayList<>();
    private List<Registro> registros = new ArrayList<>();

    public App build(){
        App app = new App(productos, registros);
        return app;
    }

    public AppBuilder withProductos(final List<Producto> aList){
        productos = aList;
        return this;
    }

    public AppBuilder withRegistros(final List<Registro> aList){
        registros = aList;
        return this;
    }
}
