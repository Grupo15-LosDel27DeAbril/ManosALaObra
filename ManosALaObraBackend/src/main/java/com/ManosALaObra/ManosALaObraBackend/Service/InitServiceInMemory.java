package com.ManosALaObra.ManosALaObraBackend.Service;

import javax.annotation.PostConstruct;

import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.AppBuilder;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.UsuarioBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;


@Service
@Transactional
public class InitServiceInMemory {

    Image imagen = new Image() {
        @Override
        public int getWidth(ImageObserver observer) {
            return 0;
        }

        @Override
        public int getHeight(ImageObserver observer) {
            return 0;
        }

        @Override
        public ImageProducer getSource() {
            return null;
        }

        @Override
        public Graphics getGraphics() {
            return null;
        }

        @Override
        public Object getProperty(String name, ImageObserver observer) {
            return null;
        }
    };

    protected final Log logger = LogFactory.getLog(getClass());

    @Value("${spring.datasource.driverClassName:NONE}")
    private String className;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AppService appService;

   @PostConstruct
   public void initialize() {
       if(className.equals("org.h2.Driver")) {
           logger.warn("Init Data Using H2 DB");
           fireInitialData();
       }
   }

   private void fireInitialData(){

       File imagen1 = new File("C:\\Users\\Alexander87\\Pictures\\boca.jpg");
       File imagen2  = new File("C:\\Users\\Alexander87\\Pictures\\LosPiojos.jpg");
       App app = new AppBuilder().withProductos(new ArrayList<Producto>())
               .build();
       Usuario usuario1 = new UsuarioBuilder().withNombreUsuario("Alex")
               .withDomicilio("avenida francia 1160")
               .withEmail("alex.quinionez@gmail.com")
               .withPassword("8787")
               .build();
       Producto producto = new Producto("Naranjas", "6 naranjas dulces", imagen1.getAbsoluteFile(), "Frutas", -34.720788, -58.254520, "Catedral de Quilmes", LocalDate.of(2020, 10, 13), LocalDate.of(2020, 10, 23));
       productoService.save(producto);
       usuario1.donarProducto(producto, app);
       producto = new Producto("Arroz", "3 paquetes de arroz de 1 kg c/u", imagen2.getAbsoluteFile(), "Alimento no perecedero", -34.746174, -58.241824, "Frigorifico", LocalDate.of(2020, 10, 05), LocalDate.of(2020, 10, 15));
       productoService.save(producto);
       usuario1.donarProducto(producto, app);


       appService.save(app);
       usuarioService.save(usuario1);
   }





}
