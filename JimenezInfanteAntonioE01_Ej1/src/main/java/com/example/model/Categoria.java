package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Categoria {

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "categoria_padre", foreignKey = @ForeignKey(name ="FK_CATEGORIA_CATEGORIA"))
    private Categoria categoriaPadre;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "categoriaPadre" ,fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    @PreRemove
    public void setCategoriasToNull(){

        productos.forEach(p -> p.setCategoria(null));
        if(categoriaPadre!=null) {
            categoriaPadre.getCategorias().remove(this);
        }
        else if(!categorias.isEmpty())
            categorias.forEach(c -> c.setCategoriaPadre(null));
    }

    public void addToCategoriaPadre(Categoria c){
        categoriaPadre = c;
        c.getCategorias().add(this);
    }

    public void removeFromCategoriaPadre(Categoria c){
        c.getCategorias().remove(this);
        categoriaPadre = null;
    }


}
