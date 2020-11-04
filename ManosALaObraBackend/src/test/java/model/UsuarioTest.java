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
    }
}
