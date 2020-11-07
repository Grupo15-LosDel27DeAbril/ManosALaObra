package com.ManosALaObra.ManosALaObraBackend.Service;

import Exceptions.UserExistException;
import com.ManosALaObra.ManosALaObraBackend.Model.*;
import com.ManosALaObra.ManosALaObraBackend.Repositories.UsuarioRepository;
import com.ManosALaObra.ManosALaObraBackend.Repositories.ProductoRepository;
import com.ManosALaObra.ManosALaObraBackend.Repositories.AppRepository;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.UsuarioBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private AppRepository appRepository;
    @Autowired
    private AppService appService;
    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private MailService mailService;
    @Autowired
    private RegistroService registroService;
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Usuario save(Usuario model) { return this.usuarioRepository.save(model);}

    public Usuario findById(Long id){ return this.usuarioRepository.findById(id).get();}

    public List<Usuario> findAll(){ return usuarioRepository.findAll();}

    /*
    @Transactional
    public Usuario updateUsuario(Usuario newUser, Long idUser){
        return usuarioRepository.findById(idUser).map(
                user -> {
                    Optional<Usuario> userExist = usuarioRepository.findByNombreUsuario(newUser.getNombreUsuario());
                    boolean userExistBool = userExist.isEmpty();
                    if(!userExistBool){
                        if(!userExist.get().getNombreUsuario().equals(user.getNombreUsuario())){
                            throw new UserExistException("Ya existe un usuario con ese nombre");
                        }
                    }
                    user.setNombreUsuario(newUser.getNombreUsuario());
                    user.setDomicilio(newUser.getDomicilio());
                    return usuarioRepository.save(user);
                }
        ).get();
    }
    */


    public Usuario agregarDonacionASistema(Producto newProducto, Long idUser, App app){
        return usuarioRepository.findById(idUser).map(
                user ->{
                    newProducto.setEmailDonante(user.getEmail());//me llevo el email de quien lo publico
                    newProducto.setEstado("Disponible");
                    newProducto.setEmailsSolicitantes(new ArrayList<Mail>());
                    newProducto.setIdDonante(user.getId());
                    productoService.save(newProducto);
                    appService.save(app);
                    user.donarProducto(newProducto, app);
                    return usuarioRepository.save(user);
                }
        ).get();
    }

    public Usuario agregarRegistroASistema(Registro newRegistro, Long idUser, App app) {

        return usuarioRepository.findById(idUser).map(
                user -> {
                    //newRegistro.setEmailSolicitante(user.getEmail());
                    registroService.save(newRegistro);
                    appService.save(app);
                    user.realizarRegistro(newRegistro, app);
                    return usuarioRepository.save(user);
                }
        ).get();
    }

    @Transactional
    public Usuario loguearWithGoogle(UsuarioLogin user){
        // Primero se busca al usuario que coincida con el mail de google logueado, si no existe
        // se lo crea y se lo devuelve para que después el frontend se encargue de gestionar los datos.
        Usuario userReturn = null;
        Optional<Usuario> userReturnOptional = this.usuarioRepository.findByEmail(user.getEmail());
        /* Si no existe un usuario con ese email, entonces se lo crea */
        if( userReturnOptional.isEmpty() ){
            userReturn = new UsuarioBuilder().withNombreUsuario(user.getEmail())
                                             .withEmail(user.getEmail())
                                             .withPassword("")
                                             .build();
            /* Se almacena el usuario en la BD */
            this.save(userReturn);
        }else{
            userReturn = userReturnOptional.get();
        }
        return userReturn;
    }


    public Usuario solicitarDonacion(String mail, Long idUser) {
        return usuarioRepository.findById(idUser).map(
                user ->{
                    /* Se deberia enviar un mail. */
                    enviarMail(user, mail);

                    return usuarioRepository.save(user);
                }).get();
    }

    public Usuario confirmarDonacion(String mail, Long idUser, Long idProd) {
        Producto producto = productoRepository.findById(idProd).get();
        return usuarioRepository.findById(idUser).map(
                user -> {
                    /* Se deberia enviar un mail de confirmacion */
                    enviarMailConfirmacion(producto, user, mail);

                    return usuarioRepository.save(user);
                }).get();
    }



    private void enviarMail(Usuario user, String mail) {
        /* Se gestiona el mail que le llegará a la persona que realizó la donacion. */
        gestionarMail(user, mail);
    }

    private void enviarMailConfirmacion(Producto producto, Usuario user, String mail) {
        /* Se gestiona el mail que le llegará a la persona befeficiaria de la donación */
        gestionarMailConfirmacion(producto,user,mail);
    }


    private void gestionarMail(Usuario user, String mail) {
        String body = "Tu donacion ha sido solicitada por el usuario con mail: " + user.getNombreUsuario()+".\n";
        String titulo = "Solicitud de donación";
        Calendar calendar = Calendar.getInstance();
        calendar.set(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), LocalDate.now().getDayOfMonth(),
                     LocalTime.now().getHour(), LocalTime.now().getMinute());

        body = body.concat("La solicitud fue realizada en fecha y hora: ") + calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR)
                + ", " + calendar.get(Calendar.HOUR_OF_DAY) + ":" +calendar.get(Calendar.MINUTE)+" hs."+".\n";

        body = body.concat("Que tenga un buen dia le desea ManosALaObra.");
        sendMailService.sendMail("manosalaobra.27.04@gmail.com", mail, titulo, body);
    }

    private void gestionarMailConfirmacion(Producto producto, Usuario user, String mail) {
        String body = "El usuario con mail: " + user.getNombreUsuario() + ", ha confirmado tu solicitud de la donación con nombre: " + producto.getNombreProducto()+".\n";
        String titulo = "Confirmación de donación solicitada";
        body = body.concat("Podrá comunicarse por este medio con dicho usuario para establecer lugar y fecha de encuentro") + ".\n";
        Calendar calendar = Calendar.getInstance();
        calendar.set(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), LocalDate.now().getDayOfMonth(),
                     LocalTime.now().getHour(), LocalTime.now().getMinute());

        body = body.concat("La confirmación fue realizada en fecha y hora: ") + calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR)
                + ", " + calendar.get(Calendar.HOUR_OF_DAY) + ":" +calendar.get(Calendar.MINUTE)+" hs."+".\n";

        body = body.concat("Que tenga un buen dia le desea ManosALaObra.");
        sendMailService.sendMail("manosalaobra.27.04@gmail.com", mail, titulo, body);
    }


    @Transactional
    public Usuario agregarMailSolicitante(Long idUser, Long idProd, App app) {

        Usuario res = usuarioRepository.findById(idUser).get();
        appService.save(app);
        for(Producto p: app.getProductos()){
            if(p.getId() == idProd){
                Mail mail = new Mail(res.getNombreUsuario());
                mailService.save(mail);
                List<Mail> mails = p.getEmailsSolicitantes();
                mails.add(mail);
                p.setEmailsSolicitantes(mails);
                p.setEstado("Pendiente");
                productoService.save(p);
            }
        }
        return res;
    }

    public Producto setearMail(Long idUser, Long idProd, App app){

        // Se encarga de agregar el mail de quien solicitó la donación .
        Usuario res = usuarioRepository.findById(idUser).get();
        appService.save(app);
        return productoRepository.findById(idProd).map(
                prod ->{
                    Mail mail = new Mail(res.getNombreUsuario());
                    mailService.save(mail);
                    List<Mail> mails = prod.getEmailsSolicitantes();
                    mails.add(mail);
                    prod.setEmailsSolicitantes(mails);
                    prod.setEstado("Pendiente");
                    Producto producto = prod;
                    productoService.save(producto);
                    this.actualizarUsuario(producto);
                    return productoService.save(prod);
                }
        ).get();
    }

    public Producto modificarFueDonado(Long idUser, Long idProd, App app) {
        // Se encarga de configurar que la donación fue realizada.
        Usuario res = usuarioRepository.findById(idUser).get();
        appService.save(app);
        return productoRepository.findById(idProd).map(
                prod -> {
                    prod.setFueDonado(true);
                    Producto producto = prod;
                    productoService.save(producto);
                    this.actualizarUsuario(producto);
                    return productoService.save(prod);
                }
        ).get();
    }

    private void actualizarUsuario(Producto producto) {
        usuarioRepository.findById(producto.getIdDonante()).map(
                user ->{
                    user.getProductos().remove(producto.getId());
                    productoService.save(producto);
                    user.getProductos().add(producto);
                    return usuarioService.save(user);
                }
        ).get();
    }

    public Usuario eliminarDonacion(Long idUser, Long idProd) {
        return usuarioRepository.findById(idUser).map(
                user -> {
                    for(Producto p: user.getProductos()) {
                        if (p.getId() == idProd) {
                            p.setFueDonado(true);
                            p.setEstado("Finalizado");
                            productoService.save(p);
                        }
                    }
                    return usuarioService.save(user);
                }).get();
    }
}


