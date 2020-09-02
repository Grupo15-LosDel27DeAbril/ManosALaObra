package model;

import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.AppBuilder;
import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.ProductoBuilder;
import com.ManosALaObra.ManosALaObraBackend.Tools.Factory.UsuarioFactory;
import com.ManosALaObra.ManosALaObraBackend.Tools.Factory.ProductoFactory;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class AppTest extends TestCase {

    private AppBuilder appBuilder = new AppBuilder();

    @Test
    public void testAgregarDonacion(){
        ProductoBuilder productoBuilder = new ProductoBuilder();
        Producto producto = productoBuilder.build();
        App app = appBuilder.build();
        assertEquals(app.cantidadDeDonaciones(), 0);
        app.agregarDonacion(producto);
        assertEquals(app.cantidadDeDonaciones(), 1);
    }

    @Test
    public void testBuscarDonacionesPorTextoIngresado(){
        Producto polenta = ProductoFactory.createWithNombre("Polenta");
        Producto arroz = ProductoFactory.createWithNombre("Arroz");
        Producto peluche = ProductoFactory.createWithNombre("Peluche");
        App app = appBuilder.build();
        app.agregarDonacion(polenta);
        app.agregarDonacion(arroz);
        app.agregarDonacion(peluche);
        List<Producto> result = app.buscarDonacionesPorTextoIngresado("o");
        assertEquals(result.size(), 2);
    }
}
