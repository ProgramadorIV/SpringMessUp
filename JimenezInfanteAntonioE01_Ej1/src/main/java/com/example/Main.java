package com.example;

import com.example.model.Categoria;
import com.example.model.Producto;
import com.example.reposiorios.CategoriaRepository;
import com.example.reposiorios.ProductoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Main {

    private final ProductoRepository productoRepo;
    private final CategoriaRepository categoriaRepo;

    @PostConstruct
    public void run(){

        Categoria c = Categoria.builder()
                .nombre("Alimentación")
                .build();

        Categoria c2 = Categoria.builder()
                .nombre("Textil")
                .build();

        Categoria c3 = Categoria.builder()
                .nombre("Editorial")
                .build();

        Categoria c4 = Categoria.builder()
                .nombre("Cosas")
                .build();

        categoriaRepo.save(c4);

        List<Categoria> categorias = List.of(c,c2,c3);

        categorias.forEach(cat -> {
            cat.addToCategoriaPadre(c4);
        });

        categoriaRepo.saveAll(categorias);

        Producto p = Producto.builder()
                .nombre("Quesito")
                .pvp(2.30)
                .build();

        p.addToCategoria(c);

        Producto p2 = Producto.builder()
                .nombre("Camiseta wapa")
                .pvp(7)
                .build();

        p2.addToCategoria(c2);

        Producto p3 = Producto.builder()
                .nombre("Silmarillion")
                .pvp(8.99)
                .build();

        p3.addToCategoria(c3);

        List<Producto> productos = List.of(p, p2, p3);

        productoRepo.saveAll(productos);

        categoriaRepo.findAll()
                .forEach( categoria -> {
                    System.out.println("\nCategoria: "+ categoria.toString());
                    categoria.getProductos().forEach(System.out::println);
                });

        if(!c.equals(c2))
            System.out.println("Todo correcto.");

        System.out.println("\nProbemos a borrar la categoría padre cosas\n");

        categoriaRepo.delete(c4);

        categoriaRepo.findAll()
                .forEach( categ -> {
                    System.out.println("Categoria: "+ categ.toString());
                    categ.getProductos().forEach(System.out::println);
                });


    }
}
