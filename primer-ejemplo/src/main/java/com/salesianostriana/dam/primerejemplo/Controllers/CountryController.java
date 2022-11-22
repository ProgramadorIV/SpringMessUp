package com.salesianostriana.dam.primerejemplo.Controllers;

import com.salesianostriana.dam.primerejemplo.Model.Country;
import com.salesianostriana.dam.primerejemplo.Repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/country/")
    public ResponseEntity<Country> newCountry(@RequestBody Country country ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(countryRepository.save(country));
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<Country> editCountry(@RequestBody Country country, @PathVariable Long id){

        return ResponseEntity.of(
                countryRepository.findById(id)
                        .map(old -> {
                            old.setCode(country.getCode());
                            old.setName(country.getName());
                            old.setShortcut(country.getShortcut());
                            return Optional.of(countryRepository.save(old));
                        }).orElse(Optional.empty())
        );
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable Long id){

        if(!countryRepository.findById(id).isEmpty())
            countryRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
