package com.example.monumentos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Monumento {


    @GeneratedValue @Id
    private Long id;

    private String codigo;
    private String pais;
    private String ciudad;
    private double longitud;
    private double latitud;
    private String nombreMonumento;
    private String descripcion;
    private String urlImagen;
}
