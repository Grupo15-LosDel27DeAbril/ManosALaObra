package com.ManosALaObra.ManosALaObraBackend.Model;

import javax.persistence.*;

@Entity
@Table(name = "BSMail")
public class Mail {

    /* Tabla de Mails */
    /* Columna id y mail */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String mail;
    private String name;
    private String motivo;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Mail(){}

    public Mail(String mail, String name, String motivo){
        this.mail = mail;
        this.name = name;
        this.motivo = motivo;
    }
}
