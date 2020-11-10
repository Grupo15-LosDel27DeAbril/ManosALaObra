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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Mail(){}

    public Mail(String mail){
        this.mail = mail;
    }
}
