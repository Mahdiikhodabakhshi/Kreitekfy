package com.kreitek.Kreitekfy.artist.application.dto;

import java.io.Serializable;

public class ArtistDTO implements Serializable {

    private Long id;
    private String name;

    public ArtistDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}