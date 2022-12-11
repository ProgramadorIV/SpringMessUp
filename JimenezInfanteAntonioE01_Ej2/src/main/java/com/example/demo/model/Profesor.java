package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Profesor {

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    @NaturalId
    private String email;

    private double puntuacion;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "profesor", fetch = FetchType.EAGER)
    private Set<CursoOnline> cursos = new HashSet<>();

    @PreRemove
    public void setCursosToNull(){
        cursos.forEach(a -> a.removeFromProfesor(this));

    }
}
