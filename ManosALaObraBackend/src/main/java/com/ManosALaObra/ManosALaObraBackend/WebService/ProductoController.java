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
    public Producto newEmailSolicitante(@PathVariable Long idUser, @PathVariable Long idProd, @PathVariable Long idApp){
        App app = appService.findById(idApp);
        return usuarioService.setearMail(idUser, idProd, app);
    }

    @CrossOrigin
    @PutMapping("/api/agregarMailConMotivo/{idProd}/{idUser}/{idApp}")
    public Producto newEmailConMotivo(@RequestBody Mail mail, @PathVariable Long idProd, @PathVariable Long idUser, @PathVariable Long idApp){
        App app = appService.findById(idApp);
        return productoService.setearMailConMotivo(mail,idProd,idUser,app);
    }

    @CrossOrigin
    @PutMapping("/api/modificarFueDonado/{idUser}/{idProd}/{idApp}")
    public Producto modificarDonacionSubida(@PathVariable Long idUser, @PathVariable Long idProd, @PathVariable Long idApp){
        App app = appService.findById(idApp);
        return usuarioService.modificarFueDonado(idUser,idProd, app);
    }

    @CrossOrigin
    @PutMapping("/api/modificarEstado/{idProd}")
    public Producto modificarEstadoDeDonacion(@PathVariable Long idProd){
        return productoService.modificarEstado(idProd);
    }

    @CrossOrigin
    @PutMapping("/api/modificarEstadoEntregado/{idProd}")
    public Producto modificarEstadoDeDonacionAEntregado(@PathVariable Long idProd){
        return productoService.modificarEstadoEntregado(idProd);
    }
}
