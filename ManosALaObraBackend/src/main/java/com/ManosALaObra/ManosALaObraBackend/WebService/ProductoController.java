package com.ManosALaObra.ManosALaObraBackend.WebService;

import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Model.Mail;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;
import com.ManosALaObra.ManosALaObraBackend.Service.ProductoService;
import com.ManosALaObra.ManosALaObraBackend.Service.UsuarioService;
import com.ManosALaObra.ManosALaObraBackend.Service.MailService;
import com.ManosALaObra.ManosALaObraBackend.Service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AppService appService;

    @CrossOrigin
    @PutMapping("/api/agregarMail/{idUser}/{idProd}/{idApp}")
    public Usuario newEmailSolicitante(@PathVariable Long idUser, @PathVariable Long idProd, @PathVariable Long idApp){
        App app = appService.findById(idApp);
        return usuarioService.agregarMailSolicitante(idUser, idProd, app);
    }
}
