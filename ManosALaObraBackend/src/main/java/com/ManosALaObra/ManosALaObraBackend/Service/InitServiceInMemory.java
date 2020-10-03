package com.ManosALaObra.ManosALaObraBackend.Service;

import javax.annotation.PostConstruct;

import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.AppBuilder;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.UsuarioBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@Transactional
public class InitServiceInMemory {

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

       App app = new AppBuilder().withProductos(new ArrayList<Producto>())
               .build();
       Usuario usuario1 = new UsuarioBuilder().withNombreUsuario("Alex")
               .withDomicilio("avenida francia 1160")
               .withEmail("alex.quinionez@gmail.com")
               .withPassword("8787")
               .build();
       Producto producto = new Producto("Naranjas", "6 naranjas dulces", "https://thumbs.dreamstime.com/z/naranjas-en-plato-36927385.jpg", "Frutas", -34.720788, -58.254520, "Catedral de Quilmes");
       productoService.save(producto);
       usuario1.donarProducto(producto, app);
       producto = new Producto("Arroz", "3 paquetes de arroz de 1 kg c/u", "https://s1.eestatic.com/2019/01/11/ciencia/nutricion/Nutricion_367724011_112039587_1706x1280.jpg", "Alimento no perecedero", -34.746174, -58.241824, "Frigorifico");
       productoService.save(producto);
       usuario1.donarProducto(producto, app);


       appService.save(app);
       usuarioService.save(usuario1);
   }





}
