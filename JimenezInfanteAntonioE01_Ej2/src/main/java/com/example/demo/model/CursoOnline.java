package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.collection.spi.PersistentList;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CursoOnline {

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    private double puntuacion;

    @ManyToOne
    @JoinColumn(name = "profesor_id", foreignKey = @ForeignKey(
            name = "FK_CURSOONLINE_PROFESOR"
    ))
    private Profesor profesor;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Video> videos = new ArrayList<>();


    public void addToProfesor(Profesor p){
        p.getCursos().add(this);
        profesor = p;
    }

    public void removeFromProfesor(Profesor p){
        p.getCursos().remove(p);
        profesor = null;
    }

}
