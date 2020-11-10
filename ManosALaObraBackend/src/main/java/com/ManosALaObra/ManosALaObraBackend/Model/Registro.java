package com.ManosALaObra.ManosALaObraBackend.Model;

import javax.persistence.*;

@Entity
@Table(name = "BSRegistro")
public class Registro {

    /* NO SE USAN LOS REGISTROS (nota mental: borrar esto!!) */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    private String emailSolicitante;
    private long idProducto;
    private String emailDonante;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getIdProducto() { return idProducto; }

    public void setIdProducto(long idProducto) { this.idProducto = idProducto; }

    public String getEmailSolicitante() { return emailSolicitante; }

    public void setEmailSolicitante(String emailSolicitante) { this.emailSolicitante = emailSolicitante; }

    public String getEmailDonante() { return emailDonante; }

    public void setEmailDonante(String emailDonante) { this.emailDonante = emailDonante; }

    public Registro(){}

    public Registro(long idProd, String emailSol, String emailDon){
        this.setIdProducto(idProd);
        this.setEmailSolicitante(emailSol);
        this.setEmailDonante(emailDon);
    }

    public Registro(long idProd, long id, String emailSol, String emailDon){
        this.setId(id);
        this.setIdProducto(idProd);
        this.setEmailSolicitante(emailSol);
        this.setEmailDonante(emailDon);
    }
}
