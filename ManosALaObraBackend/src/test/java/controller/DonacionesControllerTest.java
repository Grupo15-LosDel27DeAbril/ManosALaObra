package controller;


import com.ManosALaObra.ManosALaObraBackend.Model.App;
import com.ManosALaObra.ManosALaObraBackend.Model.Producto;
import com.ManosALaObra.ManosALaObraBackend.Model.Usuario;
import com.ManosALaObra.ManosALaObraBackend.Service.AppService;
import com.ManosALaObra.ManosALaObraBackend.Service.ProductoService;
import com.ManosALaObra.ManosALaObraBackend.Service.UsuarioService;
import com.ManosALaObra.ManosALaObraBackend.WebService.DonacionesController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
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

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;


@ContextConfiguration(classes = DonacionesController.class)
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DonacionesController.class)
public class DonacionesControllerTest {

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

    @After
    public void tearDown(){
        productoService.deleteAll();
    }


    @Test
    public void getAllDonacionesTest() {
        Assert.assertEquals(productoService.findAll().size(), 0);
    }

    @Test
    public void testObtenerDonacionPorId() throws Exception{

        Long id = 1L;

        Producto producto1 = new Producto("Naranjas", "6 naranjas dulces", "https://thumbs.dreamstime.com/z/naranjas-en-plato-36927385.jpg", "Frutas", -34.720659, -58.254300, "Catedral de Quilmes");

        given(productoService.findById(id)).willReturn(producto1);

        mockMvc.perform(get("/api/donaciones/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.nombreProducto", is(producto1.getNombreProducto())))
                .andExpect(jsonPath("$.descripcion", is(producto1.getDescripcion())))
                .andExpect(jsonPath("$.lugar", is(producto1.getLugar())));
    }

    @Test
    public void testRealizarDonacion() throws Exception{

        Long idUser = 1L;
        Long idApp = 1L;

        Usuario usuario = new Usuario("Alexander", "calle falsa 123", "alexander@gmail.com", "8787");
        usuario.setId(1L);

        Producto producto = new Producto("Polenta", "Un paquete de polenta en buen estado", "una imagen", "Alimento", -36.657634, -58.532456, "La Plata");
        producto.setId(1L);

        App app = new App(new ArrayList<Producto>());

        given(usuarioService.agregarDonacionASistema(producto, usuario.getId(), app)).willReturn(usuario);

        mockMvc.perform(post("/api/donarProducto/{idUser}/{idApp}", idUser, idApp)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());

    }



}

