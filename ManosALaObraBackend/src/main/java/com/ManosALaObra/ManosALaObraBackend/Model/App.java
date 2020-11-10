package com.ManosALaObra.ManosALaObraBackend.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BSApp")
public class App {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToMany(targetEntity = Producto.class)
    @JoinColumn(name="pd_fk",referencedColumnName = "id")
    private List<Producto> productos;


    @OneToMany(targetEntity = Registro.class)
    @JoinColumn(name="rg_fk",referencedColumnName = "id")
    private List<Registro> registros;
    // No se usan los registros (nota mental: borrar esto!!).


    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) { this.productos = productos; }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public App(){}

    public App(List<Producto> productos, List<Registro> registros) {

        this.setProductos(productos);
        this.setRegistros(registros);
    }

    public void agregarDonacion(Producto producto){
        this.getProductos().add(producto);
    }

    public void agregarRegistro(Registro registro){
        this.getRegistros().add(registro);
    }

    public int cantidadDeDonaciones(){ return this.getProductos().size();}

    public List<Producto> buscarDonacionesPorTextoIngresado(String txt){
        if(txt == ""){
            return this.getProductos();
        }else{
            List<Producto> resultado = new ArrayList<>();
            for(Producto p: productos){
                if(p.getNombreProducto().contains(txt)){
                    resultado.add(p);
                }
            }
         return resultado;
        }
    }
}
