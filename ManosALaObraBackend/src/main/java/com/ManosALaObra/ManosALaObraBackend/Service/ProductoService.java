package com.ManosALaObra.ManosALaObraBackend.Service;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Producto save(Producto model){
        return this.productoRepository.save(model);
    }

    public Producto findById(Long id){ return this.productoRepository.findById(id).get();}

    public List<Producto> findAll(){ return productoRepository.buscarTodosLosProductos();}

    /*
    public List<Producto> buscarProductosPorConsulta(String consulta){
        return productoRepository.findByNombreProductoContaining(consulta);
    }

    public List<Producto> buscarProductosPorCategoria(String categoria){
        return productoRepository.findByCategoriaProductoContaining(categoria);
    }
*/
}
