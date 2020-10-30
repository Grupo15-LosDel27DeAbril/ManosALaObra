package com.ManosALaObra.ManosALaObraBackend.Service;

import com.ManosALaObra.ManosALaObraBackend.Model.Registro;
import com.ManosALaObra.ManosALaObraBackend.Repositories.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;


    @Transactional
    public Registro save(Registro model) { return this.registroRepository.save(model);}

    public Registro findById(Long id){ return this.registroRepository.findById(id).get();}

    public List<Registro> findAll() {return this.registroRepository.buscarTodosLosRegistros();}

}
