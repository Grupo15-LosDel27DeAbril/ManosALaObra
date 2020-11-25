package model;

import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.AppBuilder;
import com.ManosALaObra.ManosALaObraBackend.Tools.Factory.UsuarioFactory;
import com.ManosALaObra.ManosALaObraBackend.Tools.Factory.ProductoFactory;
import com.ManosALaObra.ManosALaObraBackend.Model.*;
import junit.framework.TestCase;
import org.junit.Test;


public class UsuarioTest extends TestCase {

    private AppBuilder appBuilder = new AppBuilder();
    private App app = appBuilder.build();


    @Test
    public void testDomicilio(){
        Usuario usuario = UsuarioFactory.createWithDomicilio("calle falsa 123");
        assertEquals(usuario.getDomicilio(), "calle falsa 123");
    }

    @Test
    public void testNombre(){
        Usuario usuario = UsuarioFactory.createWithNombre("Alex");
        assertEquals(usuario.getNombreUsuario(), "Alex");
    }

    @Test
    public void testDonarUnProducto(){
        Usuario usuario = UsuarioFactory.anyUsuario();
        usuario.donarProducto(ProductoFactory.createWithNombre("Banana"), app);
        assertEquals(app.getProductos().size(), 1);
        assertEquals(usuario.getProductos().size(), 1);
        assertEquals(usuario.getProductos().get(0).getNombreProducto(), "Banana");
    }

    @Test
    public void testDistanciaUnPuntoAOtro(){
        Usuario usuario = UsuarioFactory.createWithLatitudeYLongitude(-34.754086, -58.249402);
        assertEquals(usuario.distance(-34.724284, -58.260713), 3.47);
    }
}
