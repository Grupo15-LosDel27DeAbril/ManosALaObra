package com.ManosALaObra.ManosALaObraBackend.WebService;

import com.ManosALaObra.ManosALaObraBackend.Model.Registro;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;
import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Service.AppService;
import com.ManosALaObra.ManosALaObraBackend.Service.UsuarioService;
import com.ManosALaObra.ManosALaObraBackend.Service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class RegistrosController {

    @Autowired
    private AppService appService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RegistroService registroService;


    @CrossOrigin
    @GetMapping("/api/registros")
    public List<Registro> listarRegistros(){
        // Registros cargados en la BD.
        return registroService.findAll();
    }

    @CrossOrigin
    @GetMapping("/api/registros/{id}")
    public Registro getRegistroConId(@PathVariable long id){
        // Busca y devuelve el registro con el id correspondiente traido por parámetro.
        return registroService.findById(id);
    }

    @CrossOrigin
    @PostMapping("/api/agregarRegistro/{idUser}/{idApp}")
    public Usuario newRegistro(@RequestBody Registro newRegistro, @PathVariable Long idUser, @PathVariable Long idApp){
        App app = appService.findById(idApp);
        return usuarioService.agregarRegistroASistema(newRegistro, idUser, app);
    }

}
