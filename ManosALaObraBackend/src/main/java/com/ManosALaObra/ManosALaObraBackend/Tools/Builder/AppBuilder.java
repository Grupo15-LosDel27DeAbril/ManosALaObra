package com.ManosALaObra.ManosALaObraBackend.Tools.Builder;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.App;

import java.util.ArrayList;
import java.util.List;

public class AppBuilder {

    private List<Producto> productos = new ArrayList<>();

    public App build(){
        App app = new App(productos);
        return app;
    }

    public AppBuilder withProductos(final List<Producto> aList){
        productos = aList;
        return this;
    }
}
