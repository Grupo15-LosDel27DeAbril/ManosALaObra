package controller;


import Exceptions.DonationExistException;
import com.ManosALaObra.ManosALaObraBackend.Model.*;
import com.ManosALaObra.ManosALaObraBackend.Service.AppService;
import com.ManosALaObra.ManosALaObraBackend.Service.ProductoService;
import com.ManosALaObra.ManosALaObraBackend.Service.UsuarioService;
import com.ManosALaObra.ManosALaObraBackend.WebService.DonacionesController;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
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
        assertEquals(productoService.findAll().size(), 0);
    }

    @Test
    public void testObtenerDonacionPorId() throws Exception{

        Long id = 1L;

        Producto producto1 = new Producto("Naranjas", "6 naranjas dulces", "https://thumbs.dreamstime.com/z/naranjas-en-plato-36927385.jpg", "Frutas", -34.720659, -58.254300, "Catedral de Quilmes", LocalDate.of(2020, 10, 13), LocalDate.of(2020, 10, 23 ), "alex.quinhonez@gmail.com", new ArrayList<Mail>(), "sin estado", 0, false);

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

        Usuario usuario = new Usuario("Alexander", "calle falsa 123", "alexander@gmail.com", "8787", new ArrayList<Producto>(), 0.0, 0.0, 0.0);
        //usuario.setId(1L);

        Producto producto = new Producto("Polenta", "Un paquete de polenta en buen estado", "una imagen", "Alimento", -36.657634, -58.532456, "La Plata", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 20), usuario.getEmail(), new ArrayList<Mail>(), "sin estado", 0, false);
        //producto.setId(1L);

        App app = new App(new ArrayList<Producto>(), new ArrayList<Registro>());

        given(appService.findById(idApp)).willReturn(app);
        given(usuarioService.findById(idUser)).willReturn(usuario);
        given(usuarioService.agregarDonacionASistema(producto, idUser, app)).willReturn(usuario);


        mockMvc.perform(post("/api/donarProducto/{idUser}/{idApp}", idUser, idApp)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

    @Test
    public void testObtenerBusquedaCorrecta() throws Exception{

        String found = "Arroz";

        Producto producto1 = new Producto("Arroz", "3 paquetes de arroz de 1 kg c/u", "https://s1.eestatic.com/2019/01/11/ciencia/nutricion/Nutricion_367724011_112039587_1706x1280.jpg", "Alimento no parecedero", -34.746174, -58.241824, "Frigorifico", LocalDate.of(2020, 10, 12), LocalDate.of(2020, 10, 22), "alex.quinhonez@gmail.com", new ArrayList<Mail>(), "sin estado", 0, false);

        ArrayList<Producto> productos = new ArrayList<Producto>();


        given(productoService.buscarProductosPorConsulta(found)).willReturn(this.agregarDonacion(productos, producto1));

        mockMvc.perform(get("/api/buscarProductos").param("q", "Arroz"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.size()", is(1)));
    }

    @Test
    public void testObtenerBusquedaIncorrecta() throws Exception{

        String found = "Pera";

        Producto producto1 = new Producto("Naranjas", "6 naranjas dulces", "https://thumbs.dreamstime.com/z/naranjas-en-plato-36927385.jpg", "Frutas", -34.720659, -58.254300, "Catedral de Quilmes", LocalDate.of(2020, 10, 11), LocalDate.of(2020, 10, 25), "alex.quinhonez@gmail.com", new ArrayList<Mail>(), "sin estado", 0, false);

        ArrayList<Producto> productos = new ArrayList<Producto>();


        given(productoService.buscarProductosPorConsulta(found)).willThrow(new DonationExistException("No existe la donacion con dicho nombre"));

        mockMvc.perform(get("/api/buscarProductos").param("q", found))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    ArrayList<Producto> agregarDonacion(ArrayList<Producto> current, Producto producto){
        current.add(producto);
        return current;
    }

}

