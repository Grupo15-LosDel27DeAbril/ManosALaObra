package com.ManosALaObra.ManosALaObraBackend.WebService;

import com.ManosALaObra.ManosALaObraBackend.Model.*;
import com.ManosALaObra.ManosALaObraBackend.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.uqbar.xtrest.api.annotation.Body;

import javax.validation.Valid;
import java.util.List;


@RestController
@EnableAutoConfiguration
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @GetMapping("/api/usuarios")
    public List<Usuario> listarUsuarios(){
        // Todos los usuarios del sistema.
        return usuarioService.findAll();
    }

    @CrossOrigin
    @GetMapping("/api/usuario/{id}")
    public Usuario getUsuarioById(@PathVariable Long id){
        // Busco y devuelvo el usuario con el id correspondiente traido por parámetro
        return usuarioService.findById(id);
    }

    @CrossOrigin
    @PostMapping("api/usuario/loginWithGoogle")
    public Usuario loginUserWithGoogle(@RequestBody UsuarioLogin user){
        // Primero se busca al usuario que coincida con el mail de google logueado, si no existe
        // se lo crea y se lo devuelve para que después el frontend se encargue de gestionar los datos.
        return usuarioService.loguearWithGoogle(user);
    }

    @CrossOrigin
    @PutMapping("/api/usuario/solicitarDonacion/{idUser}/{idProd}")
    public Usuario solicitarDonacionSubida(@PathVariable Long idUser, @PathVariable Long idProd){
       return usuarioService.solicitarDonacion(idUser, idProd);
    }

    @CrossOrigin
    @PutMapping("/api/usuario/confirmarDonacion/{idUser}/{idProd}")
    public Usuario confirmarDonacionSubida(@RequestBody String mail, @PathVariable Long idUser, @PathVariable Long idProd){
        return usuarioService.confirmarDonacion(mail,idUser,idProd);
    }

    @CrossOrigin
    @PutMapping("/api/usuario/eliminarDonacion/{idUser}/{idProd}")
    public Usuario eliminarDonacionSubida(@PathVariable Long idUser, @PathVariable Long idProd){
        return usuarioService.eliminarDonacion(idUser, idProd);
    }

    @CrossOrigin
    @PutMapping("/api/usuario/establecerUbicacion/{idUser}")
    public Usuario coordenadasUsuario(@RequestBody Geo coord, @PathVariable Long idUser){
        return usuarioService.coordenadasActual(coord, idUser);
    }

    @CrossOrigin
    @PutMapping("/api/usuario/distancia/{idUser}")
    public Usuario distanciaEntreCoordenadas(@RequestBody Geo coord, @PathVariable Long idUser){
        return usuarioService.distanciaDeUnPuntoAOtro(coord, idUser);
    }

}
