package com.example.ejercicio;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Monumento {

    @GeneratedValue @Id
    private Long id;

    private String nombre;

}
