package com.DesafioTinnova.DesafioTinnova.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "marcas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Marca {

    @Id
    private String name;

    public Marca(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
