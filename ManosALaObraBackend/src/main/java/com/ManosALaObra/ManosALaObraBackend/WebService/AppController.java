package com.ManosALaObra.ManosALaObraBackend.WebService;


import com.ManosALaObra.ManosALaObraBackend.Model.*;
import com.ManosALaObra.ManosALaObraBackend.Service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.uqbar.xtrest.api.annotation.Body;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class AppController {
    @Autowired
    private AppService appService;
}
