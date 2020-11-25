package com.ManosALaObra.ManosALaObraBackend.Model;

import javax.persistence.*;

@Entity
@Table(name = "BSGeo")
public class Geo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private Double latitude;
    private Double longitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Geo(){}

    public Geo(Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
