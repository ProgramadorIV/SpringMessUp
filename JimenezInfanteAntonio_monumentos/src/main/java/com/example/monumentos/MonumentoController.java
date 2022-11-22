package com.example.monumentos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MonumentoController {

    private final MonumentoRepository repo;

    @GetMapping("/monumentos/")
    public ResponseEntity<List<Monumento>> mostrarMonumentos(){
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/monumentos/{id}")
    public ResponseEntity<Monumento> mostrarMonumentoPorId(@PathVariable("id") Long id){
        return ResponseEntity.of(repo.findById(id));
    }

    @PostMapping("/monumentos/")
    public ResponseEntity<Monumento> nuevoMonumento(@RequestBody Monumento monumento ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repo.save(monumento));
    }

    @PutMapping("/monumentos/{id}")
    public ResponseEntity<Monumento> editarMonumento(@RequestBody Monumento monumento, @PathVariable Long id){

        return ResponseEntity.of(
                repo.findById(id)
                        .map(monument -> {
                            monument.setNombreMonumento(monumento.getNombreMonumento());
                            monument.setPais(monumento.getPais());
                            monument.setCiudad(monumento.getCiudad());
                            monument.setDescripcion(monumento.getDescripcion());
                            monument.setLongitud(monumento.getLongitud());
                            monument.setLatitud(monumento.getLatitud());
                            monument.setUrlImagen(monumento.getUrlImagen());
                            return Optional.of(repo.save(monument));
                        }).orElse(Optional.empty())
        );
    }

    @DeleteMapping("/monumentos/{id}")
    public ResponseEntity<Monumento> borrarMonumento(@PathVariable Long id){

        if(!repo.findById(id).isEmpty())
            repo.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
