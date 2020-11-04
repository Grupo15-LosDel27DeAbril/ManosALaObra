package com.ManosALaObra.ManosALaObraBackend.Repositories;

import com.ManosALaObra.ManosALaObraBackend.Model.Mail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.context.annotation.Configuration;
import java.util.List;

import java.util.Optional;

@Configuration
@Repository
public interface MailRepository extends CrudRepository<Mail, Integer>{

    Optional<Mail> findById(Long id);

    List<Mail> findAll();

}
