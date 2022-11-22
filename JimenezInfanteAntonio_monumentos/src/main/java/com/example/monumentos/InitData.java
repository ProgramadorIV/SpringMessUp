package com.example.monumentos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final MonumentoRepository repo;

    @PostConstruct
    public void initialize(){
        Monumento giralda = new Monumento();

        giralda.setCiudad("Sevilla");
        giralda.setCodigo("es");
        giralda.setPais("Españita");
        giralda.setDescripcion("Una torre alta");
        giralda.setNombreMonumento("Giralda");
        giralda.setUrlImagen("http://giralda.img");
        giralda.setLatitud(0.33);
        giralda.setLongitud(0.33);

        repo.save(giralda);

    }
}
