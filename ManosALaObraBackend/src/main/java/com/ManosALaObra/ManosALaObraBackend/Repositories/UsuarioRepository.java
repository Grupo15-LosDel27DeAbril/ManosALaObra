package com.ManosALaObra.ManosALaObraBackend.Repositories;

import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findById(Long id);

    List<Usuario> findAll();

    @Query(value = "Select * from BSUsuario where email like %?1%", nativeQuery = true)
    Optional<Usuario> findByEmail(String email);

    @Query(value = "SELECT * from BSUsuario where nombre_usuario like ?1", nativeQuery = true)
    Optional<Usuario> findByNombreUsuario(String name);

}
