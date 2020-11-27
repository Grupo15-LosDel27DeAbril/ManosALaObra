package controller;

import com.ManosALaObra.ManosALaObraBackend.Model.*;
import com.ManosALaObra.ManosALaObraBackend.Service.AppService;
import com.ManosALaObra.ManosALaObraBackend.Service.ProductoService;
import com.ManosALaObra.ManosALaObraBackend.Service.UsuarioService;
import com.ManosALaObra.ManosALaObraBackend.WebService.UsuarioController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import org.springframework.http.MediaType;

@ContextConfiguration(classes = UsuarioController.class)
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsuarioController.class)
public class UsuarioControllerTest {

    @MockBean
    private ProductoService productoService;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private AppService appService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testEstablecerCoordenadasActual() throws Exception {

        Long idUSer = 1L;

        Usuario user = new Usuario("Alex", "Avenida Francia 1160", "alex.quinhonez@gmil.com", "1234", new ArrayList<Producto>(), -34.754002, -58.249307, 0.0);

        Geo coord = new Geo(-34.754002, -58.249307);

        given(usuarioService.findById(1L)).willReturn(user);
        given(usuarioService.coordenadasActual(coord, 1L)).willReturn(user);

        mockMvc.perform(put("/api/usuario/establecerUbicacion/{idUser}", idUSer)
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(user)))
               .andExpect(MockMvcResultMatchers.status().isOk());


    }

}
