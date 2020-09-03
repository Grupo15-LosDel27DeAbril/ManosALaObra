package com.ManosALaObra.ManosALaObraBackend.WebService;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;
import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Service.AppService;
import com.ManosALaObra.ManosALaObraBackend.Service.ProductoService;
import com.ManosALaObra.ManosALaObraBackend.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class DonacionesController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AppService appService;

    @CrossOrigin
    @GetMapping("/api/donaciones")
    public List<Producto> listarDonaciones(){
        // Donaciones cargadas en la app.
        return productoService.findAll();
    }

    @CrossOrigin
    @GetMapping("/api/donaciones/{id}")
    public Producto getDonacionConId(@PathVariable long id){
        // Busca y devuelve la donación con el id correspondiente traido por parámetro.
        return productoService.findById(id);
    }

    @CrossOrigin
    @GetMapping("/api/apps")
    public List<App> listarApps(){
        return appService.findAll();
    }

    @CrossOrigin
    @PostMapping("/api/donarProducto/{idUser}/{idApp}")
    Usuario newDonacion(@RequestBody Producto newProducto, @PathVariable Long idUser, @PathVariable Long idApp){
        App app = appService.findById(idApp);
        return usuarioService.agregarDonacionASistema(newProducto, idUser, app);
    }

    @CrossOrigin
    @GetMapping("api/buscarProductos")
    public List<Producto> buscarProductos(@RequestParam("q") String consulta){
        return productoService.buscarProductosPorConsulta(consulta);
    }

    @CrossOrigin
    @GetMapping("api/buscarPorCategoria")
    public List<Producto> buscarPorCategoria(@RequestParam("q") String categoria){
        return productoService.buscarProductosPorCategoria(categoria);
    }








}