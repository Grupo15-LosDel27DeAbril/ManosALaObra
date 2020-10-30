package com.ManosALaObra.ManosALaObraBackend.Service;

import com.ManosALaObra.ManosALaObraBackend.Model.Mail;
import com.ManosALaObra.ManosALaObraBackend.Repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private MailRepository mailRepository;

    @Transactional
    public Mail save(Mail model){
        return this.mailRepository.save(model);
    }

    public Mail findById(Long id){ return this.mailRepository.findById(id).get();}

    public List<Mail> findAll() { return this.mailRepository.findAll();}

    @Transactional
    public void deleteAll() { mailRepository.deleteAll();}

}
