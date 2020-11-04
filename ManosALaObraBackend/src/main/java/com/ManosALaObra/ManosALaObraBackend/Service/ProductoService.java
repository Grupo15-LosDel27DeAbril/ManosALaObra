package com.ManosALaObra.ManosALaObraBackend.Service;

import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Model.Mail;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;
import com.ManosALaObra.ManosALaObraBackend.Repositories.ProductoRepository;
import com.ManosALaObra.ManosALaObraBackend.Service.MailService;
import com.ManosALaObra.ManosALaObraBackend.Service.AppService;
import com.ManosALaObra.ManosALaObraBackend.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private AppService appService;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Producto save(Producto model){
        return this.productoRepository.save(model);
    }

    @Transactional
    public void delete(Long id){
        productoRepository.delete(this.findById(id));
    }

    public Producto findById(Long id){ return this.productoRepository.findById(id).get();}

    public List<Producto> findAll(){ return productoRepository.buscarTodosLosProductos();}


    public List<Producto> buscarProductosPorConsulta(String consulta){
        return productoRepository.findByNombreProductoContaining(consulta);
    }

    public List<Producto> buscarProductosPorCategoria(String categoria){
        return productoRepository.findByCategoriaProductoContaining(categoria);
    }

    @Transactional
    public void deleteAll(){
        productoRepository.deleteAll();
    }

    /*
    public Producto agregarMailSolicitante(Usuario usuario, Long idProd, App app) {
        return productoRepository.findById(idProd).map(
                prod ->{
                    appService.save(app);
                    usuarioService.save(usuario);
                    Mail mail = new Mail(usuario.getNombreUsuario());
                    mailService.save(mail);
                    prod.agregarMail(mail);
                    return productoRepository.save(prod);
                }
        ).get();
    }
    */
}
