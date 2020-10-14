package model;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.ProductoBuilder;
import com.ManosALaObra.ManosALaObraBackend.Tools.Factory.ProductoFactory;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDate;


public class ProductoTest extends TestCase {

    private ProductoBuilder productoBuilder = new ProductoBuilder();

    @Test
    public void testFechaDePublicacion(){
        productoBuilder.withFechaPublicacion(LocalDate.of(2020, 10, 03));
        Producto producto = productoBuilder.build();
        assertEquals(producto.getFechaPublicacion().getYear(), 2020);
        assertEquals(producto.getFechaPublicacion().getMonth().getValue(), 10);
        assertEquals(producto.getFechaPublicacion().getDayOfMonth(), 03);
    }

    @Test
    public void testFechaLimitePublicacion(){
        productoBuilder.withFechaLimite(LocalDate.now());
        Producto producto = productoBuilder.build();
        assertEquals(producto.getValidoHasta().getYear(), 2020);
        assertEquals(producto.getValidoHasta().getMonth().getValue(), 10);
        assertEquals(producto.getValidoHasta().getDayOfMonth(), 13);
    }
}
