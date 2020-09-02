package com.ManosALaObra.ManosALaObraBackend.Service;

import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Repositories.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppService {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private ProductoService productoService;

    @Transactional
    public App save(App model){
        return this.appRepository.save(model);
    }

    public App findById(Long id){ return this.appRepository.findById(id).get();}

    public List<App> findAll() { return this.appRepository.findAll();}


    public App agregarDonacionEnLaApp(Long idApp, Long idProducto){
       /*  a partir del id de la app y de los productos, agrego la donación de dicho producto a la app correspondiente.*/
        App app = findById(idApp);
        app.agregarDonacion(productoService.findById(idProducto));
        return save(app);
    }

}

