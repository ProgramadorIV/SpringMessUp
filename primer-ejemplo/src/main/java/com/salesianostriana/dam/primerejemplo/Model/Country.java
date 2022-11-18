package com.salesianostriana.dam.primerejemplo.Model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Country {

    private String code;
    private String name;
    private String shortcut;
}
