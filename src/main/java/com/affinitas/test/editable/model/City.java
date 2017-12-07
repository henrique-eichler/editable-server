package com.affinitas.test.editable.model;

import javax.persistence.Embeddable;

@Embeddable
public class City {

    private String city;
    private String lat;
    private String lon;

    public City() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
