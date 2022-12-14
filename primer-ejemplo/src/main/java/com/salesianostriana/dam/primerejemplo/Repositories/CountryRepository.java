package com.salesianostriana.dam.primerejemplo.Repositories;

import com.salesianostriana.dam.primerejemplo.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findFirstByCode(String code);


}
