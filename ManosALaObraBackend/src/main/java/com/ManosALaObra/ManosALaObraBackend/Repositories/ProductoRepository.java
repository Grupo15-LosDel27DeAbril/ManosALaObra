package com.ManosALaObra.ManosALaObraBackend.Repositories;

import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.context.annotation.Configuration;
import java.util.List;

import java.util.Optional;

@Configuration
@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

    Optional<Producto> findById(Long id);

    List<Producto> findAll();



    /**Realizo la busqueda de todas las donaciones que esten guardados en la base de datos**/
    @Query(value = "Select * from BSProducto order by nombre", nativeQuery = true)
    List<Producto> buscarTodosLosProductos();


    /**Realizo la búsqueda de todas las donaciones de productos cuyo Nombre tenga contenida el string "consulta" traido por parametro**/
    @Deprecated
    @Query(value = "Select * from BSProducto where nombre like %?1%", nativeQuery = true)
    List<Producto> buscarProductosPorConsulta(String consulta);


    List<Producto> findByNombreProductoContaining(String consulta);


    /** Realizo la búsqueda de todas las donaciones de productos cuya categoria tenga contenido el string "categoria" traido por parámetro**/
    @Query(value = "Select * from BSProducto where categoria like %?1%", nativeQuery = true)
    List<Producto> findByCategoriaProductoContaining(String categoria);

}
