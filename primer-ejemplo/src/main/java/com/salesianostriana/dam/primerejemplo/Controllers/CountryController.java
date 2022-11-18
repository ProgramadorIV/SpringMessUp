package com.salesianostriana.dam.primerejemplo.Controllers;

import com.salesianostriana.dam.primerejemplo.Model.Country;
import com.salesianostriana.dam.primerejemplo.Repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryRepository countryRepository;

    @GetMapping("/country")
    public ResponseEntity<List<Country>> showCountries(){
        return ResponseEntity.ok(countryRepository.findAll());
    }

    @GetMapping("/country/{code}")
    public ResponseEntity<Country> getOneCountry(@PathVariable String code){
        return ResponseEntity.of(countryRepository.findFirstByCode(code));
    }
}
