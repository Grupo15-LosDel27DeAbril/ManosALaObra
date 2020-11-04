package com.ManosALaObra.ManosALaObraBackend.Repositories;

import com.ManosALaObra.ManosALaObraBackend.Model.Registro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.context.annotation.Configuration;
import java.util.List;

import java.util.Optional;

@Configuration
@Repository
public interface RegistroRepository extends CrudRepository<Registro, Integer>{

    Optional<Registro> findById(Long id);

    List<Registro> findAll();

    /**Realizo la busqueda de todos los registros que esten guardados en la base de datos**/
    @Query(value = "Select * from BSRegistro order by email", nativeQuery = true)
    List<Registro> buscarTodosLosRegistros();

}
