package com.salesianostriana.dam.primerejemplo;

import com.salesianostriana.dam.primerejemplo.Model.Country;
import com.salesianostriana.dam.primerejemplo.Repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final CountryRepository repo;

    @PostConstruct
    public void initialize(){

        Country espanita = new Country();

        espanita.setCode("es");
        espanita.setName("España");
        espanita.setCapital("Madrid");

        repo.save(espanita);

        Country portugal = new Country();

        portugal.setCode("po");
        portugal.setName("Portugal");
        portugal.setCapital("Lisboa");

        repo.save(portugal);

    }
}
