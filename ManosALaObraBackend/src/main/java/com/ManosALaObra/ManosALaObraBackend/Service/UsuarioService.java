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
        // Se agrega una nueva donación a la app.
        // Su estado se setea en "disponible".
        // Tiene una lista de mails solicitantes vacía.
        return usuarioRepository.findById(idUser).map(
                user ->{
                    newProducto.setEmailDonante(user.getEmail());//me llevo el email de quien lo publicó
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


    public Usuario solicitarDonacion(Long idUser, Long idProd) {
        // Se solicita determianda donación subida en la app.
        Producto producto = productoRepository.findById(idProd).get();
        return usuarioRepository.findById(idUser).map(
                user ->{
                    /* Se deberia enviar un mail. */
                    enviarMail(user, producto);

                    return usuarioRepository.save(user);
                }).get();
    }

    public Usuario confirmarDonacion(String mail, Long idUser, Long idProd) {
        /*
           Este método se encarga de configurar el envío de mail que notificará al usuario que
           solicitó la donación que será el beneficiario del mismo. El string "mail" es el mail
           del usuario beneficiado.
        */
        Producto producto = productoRepository.findById(idProd).get();
        return usuarioRepository.findById(idUser).map(
                user -> {
                    /* Se deberia enviar un mail de confirmacion */
                    enviarMailConfirmacion(producto, user, mail);

                    return usuarioRepository.save(user);
                }).get();
    }



    private void enviarMail(Usuario user, Producto producto) {
        /* Se gestiona el mail que le llegará a la persona que realizó la donacion. */
        gestionarMail(user, producto);
    }

    private void enviarMailConfirmacion(Producto producto, Usuario user, String mail) {
        /* Se gestiona el mail que le llegará a la persona befeficiaria de la donación */
        gestionarMailConfirmacion(producto,user,mail);
    }


    private void gestionarMail(Usuario user, Producto producto) {
        String body = "Tu donacion con nombre: " + producto.getNombreProducto() + ", ha sido solicitada por el usuario con mail: " + user.getNombreUsuario()+".\n";
        String titulo = "Solicitud de donación";
        Calendar calendar = Calendar.getInstance();
        calendar.set(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), LocalDate.now().getDayOfMonth(),
                     LocalTime.now().getHour(), LocalTime.now().getMinute());

        body = body.concat("La solicitud fue realizada en fecha y hora: ") + calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR)
                + ", " + calendar.get(Calendar.HOUR_OF_DAY) + ":" +calendar.get(Calendar.MINUTE)+" hs."+".\n";

        body = body.concat("Que tenga un buen dia le desea ManosALaObra.");
        sendMailService.sendMail("manosalaobra.27.04@gmail.com", producto.getEmailDonante(), titulo, body);
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
                Mail mail = new Mail(res.getNombreUsuario(),"","");
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
        // Pueden llegar varias solicitudes, varios mails.
        // Dicho mail se agrega a la lista de solicitantes de la donación.
        // Una vez hecho esto, la donación pasa de estar de "Disponible" a "Pendiente".
        Usuario res = usuarioRepository.findById(idUser).get();
        appService.save(app);
        return productoRepository.findById(idProd).map(
                prod ->{
                    Mail mail = new Mail(res.getNombreUsuario(),"","");  // Se guarda como dato el mail del usuario solicitante.
                    mailService.save(mail);
                    List<Mail> mails = prod.getEmailsSolicitantes();
                    mails.add(mail);  // Se agrega a la lista de mails solicitantes un nuevo mail.
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
        // Dicha configuración se realiza seteando el booleano que indica que fue donado en "true".
        // Esto nos servirá para determinar si la donación puede marcarse como entregada o no.
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

    public void actualizarUsuario(Producto producto) {
        // Se actualiza la lista de donaciones del usuario donante.
        // Se actualiza sus donaciones con sus respectivos solicitantes.
        // Cada donación almacena el id de su usuario donante.
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
        /*
           Si bien no se elimina la donación del sistema, queda registrado en la app con el estado
           "Finalizado" y que fue donado. Una vez registrado con este estado, nadie podrá
            volver a solicitarlo.
         */
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


