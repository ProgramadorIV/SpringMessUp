package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Video {

    @Id @GeneratedValue
    private Long id;

    private int orden;

    private String titulo, descripcion;

    @NaturalId
    private String url;

    @ManyToOne
    @JoinColumn(name = "curso_id", foreignKey = @ForeignKey(
            name = "FK_VIDEO_CURSOONLINE"
    ))
    private CursoOnline curso;


    public void addToCurso(CursoOnline c){
        c.getVideos().add(this);
        curso = c;
    }

    public void removeFromCurso(CursoOnline c){
        c.getVideos().remove(this);
        curso = null;
    }

}
