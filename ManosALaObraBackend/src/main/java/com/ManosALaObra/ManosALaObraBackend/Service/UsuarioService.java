package com.ManosALaObra.ManosALaObraBackend.Service;

import Exceptions.UserExistException;
import com.ManosALaObra.ManosALaObraBackend.Model.*;
import com.ManosALaObra.ManosALaObraBackend.Repositories.UsuarioRepository;
import com.ManosALaObra.ManosALaObraBackend.Repositories.ProductoRepository;
import com.ManosALaObra.ManosALaObraBackend.Repositories.AppRepository;
import com.ManosALaObra.ManosALaObraBackend.Tools.Builder.UsuarioBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
                    productoService.save(newProducto);
                    appService.save(app);
                    user.donarProducto(newProducto, app);
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


}

