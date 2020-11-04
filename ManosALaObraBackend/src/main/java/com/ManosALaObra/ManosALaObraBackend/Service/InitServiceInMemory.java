package com.ManosALaObra.ManosALaObraBackend.Service;

import javax.annotation.PostConstruct;

import com.ManosALaObra.ManosALaObraBackend.Model.*;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.AppBuilder;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.UsuarioBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    private RegistroService registroService;

   @PostConstruct
   public void initialize() {
       if(className.equals("org.h2.Driver")) {
           logger.warn("Init Data Using H2 DB");
           fireInitialData();
       }
   }

   private void fireInitialData(){

       App app = new AppBuilder().withProductos(new ArrayList<Producto>())
               .withRegistros(new ArrayList<Registro>())
               .build();
       Usuario usuario1 = new UsuarioBuilder().withNombreUsuario("alex.quinhonez@gmail.com")
               .withDomicilio("avenida francia 1160")
               .withEmail("alex.quinonez@gmail.com")
               .withPassword("8787")
               .build();
       Producto producto = new Producto("Naranjas", "6 naranjas dulces", "https://thumbs.dreamstime.com/z/naranjas-en-plato-36927385.jpg", "Frutas", -34.720788, -58.254520, "Catedral de Quilmes", LocalDate.of(2020, 10, 13), LocalDate.of(2020, 10, 23), usuario1.getNombreUsuario(), new ArrayList<Mail>(), "Disponible");
       productoService.save(producto);
       usuario1.donarProducto(producto, app);
       producto = new Producto("Arroz", "3 paquetes de arroz de 1 kg c/u", "https://s1.eestatic.com/2019/01/11/ciencia/nutricion/Nutricion_367724011_112039587_1706x1280.jpg", "Alimento no perecedero", -34.746174, -58.241824, "Frigorifico", LocalDate.of(2020, 10, 05), LocalDate.of(2020, 10, 15), "alan.nicolas.rodriguez@gmail.com", new ArrayList<Mail>(), "Disponible");
       productoService.save(producto);
       usuario1.donarProducto(producto, app);

       appService.save(app);
       usuarioService.save(usuario1);
   }





}
