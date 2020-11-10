package controller;

import com.ManosALaObra.ManosALaObraBackend.Model.*;
import com.ManosALaObra.ManosALaObraBackend.Service.ProductoService;
import com.ManosALaObra.ManosALaObraBackend.Service.UsuarioService;
import com.ManosALaObra.ManosALaObraBackend.Service.MailService;
import com.ManosALaObra.ManosALaObraBackend.Service.AppService;
import com.ManosALaObra.ManosALaObraBackend.WebService.ProductoController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.http.MediaType;


@ContextConfiguration(classes = ProductoController.class)
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductoController.class)
public class DonacionControllerTest {

    @MockBean
    private ProductoService productoService;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private MailService mailService;

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
    public void testModificarAEstadoFinalizado() throws Exception{

        Long id = 1L;

        Producto producto1 = new Producto("Naranjas", "6 naranjas dulces", "https://thumbs.dreamstime.com/z/naranjas-en-plato-36927385.jpg", "Frutas", -34.720659, -58.254300, "Catedral de Quilmes", LocalDate.of(2020, 10, 13), LocalDate.of(2020, 10, 23 ), "alex.quinhonez@gmail.com", new ArrayList<Mail>(), "sin estado", 0, false);

        given(productoService.findById(id)).willReturn(producto1);

        mockMvc.perform(put("/api/modificarEstado/{idProd}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto1)))
                .andExpect(MockMvcResultMatchers.status().isOk());



    }
}
