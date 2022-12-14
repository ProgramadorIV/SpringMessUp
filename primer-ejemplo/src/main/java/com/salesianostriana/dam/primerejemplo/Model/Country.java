package com.salesianostriana.dam.primerejemplo.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Country {

    @GeneratedValue @Id
    private Long id;
    private String code;
    private String name;
    private String capital;
}
