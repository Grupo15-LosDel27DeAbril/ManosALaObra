package model;

import com.ManosALaObra.ManosALaObraBackend.Model.Mail;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.ProductoBuilder;
import com.ManosALaObra.ManosALaObraBackend.Tools.Factory.ProductoFactory;
import junit.framework.TestCase;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ProductoTest extends TestCase {

    private ProductoBuilder productoBuilder = new ProductoBuilder();

    @Test
    public void testFechaDePublicacion(){
        productoBuilder.withFechaPublicacion(LocalDate.of(2020, 10, 03));
        Producto producto = productoBuilder.build();
        assertEquals(producto.getFechaPublicacion(), "03-10-2020");
    }

    @Test
    public void testFechaLimitePublicacion(){
        productoBuilder.withFechaLimite(LocalDate.now());
        Producto producto = productoBuilder.build();
        assertEquals(producto.getValidoHasta(), "09-11-2020");
    }

    @Test
    public void testProbarFechas(){
        assertEquals(1,1);
        LocalDate fecha1 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String text = fecha1.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);

        System.out.println("la fecha es: " + fecha1.format(formatter));
        System.out.println("la otra fecha es: " + text);
    }
}
