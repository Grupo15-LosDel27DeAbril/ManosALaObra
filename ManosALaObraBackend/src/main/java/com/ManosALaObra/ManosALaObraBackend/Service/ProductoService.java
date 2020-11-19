package com.ManosALaObra.ManosALaObraBackend.Service;

import Exceptions.DonationExistException;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
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


    public List<Producto> buscarProductosPorConsulta(String consulta) throws Exception {
        List<Producto> result = productoRepository.findByNombreProductoContaining(consulta);
        if(result.size() == 0){
            throw new DonationExistException("No existe la donacion con dicho nombre");
        } else {
            return productoRepository.findByNombreProductoContaining(consulta);
        }
    }

    public List<Producto> buscarProductosPorCategoria(String categoria){
        return productoRepository.findByCategoriaProductoContaining(categoria);
    }

    public List<Producto> buscarProductosPorEstado(String estado) {
        return productoRepository.findByEstadoProductoContaining(estado);
    }

    public List<Producto> filtrarNoEntregados(){
        List<Producto> result = new ArrayList<Producto>();
        for(Producto p: this.findAll()){
            if(p.getEstado() != "Entregado"){
                this.save(p);
                result.add(p);
            }
        }
      return result;
    }

    @Transactional
    public void deleteAll(){
        productoRepository.deleteAll();
    }

    public Producto modificarEstado(Long idProd) {
        // Se modifica el estado de la donación de "pendiente" a "finalizado".
        return productoRepository.findById(idProd).map(
                prod ->{
                    prod.setEstado("Finalizado");
                    prod.setFueDonado(true);
                    return this.save(prod);
                }).get();
    }

    public Producto modificarEstadoEntregado(Long idProd) {
        // Se modifica el estado de la donación de "finalizado" a "entregado."
        return productoRepository.findById(idProd).map(
                prod ->{
                    prod.setEstado("Entregado");
                    return this.save(prod);
                }).get();
    }

    public Producto setearMailConMotivo(Mail mail, Long idProd, Long idUser,App app) {
        Usuario res = usuarioService.findById(idUser);
        appService.save(app);
        return productoRepository.findById(idProd).map(
                prod ->{
                    Mail mailRes = new Mail(res.getNombreUsuario(), mail.getName(), mail.getMotivo());
                    mailService.save(mailRes);
                    List<Mail> mails = prod.getEmailsSolicitantes();
                    mails.add(mailRes);
                    prod.setEmailsSolicitantes(mails);
                    prod.setEstado("Pendiente");
                    Producto producto = prod;
                    this.save(producto);
                    usuarioService.actualizarUsuario(producto);
                    return this.save(prod);
                }
        ).get();
    }
}
