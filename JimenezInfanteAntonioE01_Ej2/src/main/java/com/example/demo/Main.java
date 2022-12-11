package com.example.demo;

import com.example.demo.model.CursoOnline;
import com.example.demo.model.Profesor;
import com.example.demo.model.Video;
import com.example.demo.repos.CursoOnlineRepository;
import com.example.demo.repos.ProfesorRepository;
import com.example.demo.repos.VideoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Main {

    private final ProfesorRepository profesorRepository;
    private final CursoOnlineRepository cursoOnlineRepository;
    private final VideoRepository videoRepository;

    @PostConstruct
    public void run(){

        Profesor p = Profesor.builder()
                .nombre("Antonio")
                .puntuacion(9.99)
                .email("antonio@gmail.com")
                .build();

        Profesor p2 = Profesor.builder()
                .nombre("Clara")
                .email("clara@gmail.com")
                .puntuacion(10)
                .build();

        profesorRepository.save(p);
        profesorRepository.save(p2);

        CursoOnline c = CursoOnline.builder()
                .nombre("Como hacer pan")
                .puntuacion(10)
                .build();

        CursoOnline c2 = CursoOnline.builder()
                .nombre("Física cuántica lvl. Diablo")
                .puntuacion(10)
                .build();

        c.addToProfesor(p);
        c2.addToProfesor(p2);

        cursoOnlineRepository.saveAll(List.of(c,c2));

        Video v = Video.builder()
                .titulo("Ingredientes necesarios")
                .orden(1)
                .descripcion("Se datallan los ingredientes necesarios para comenzar a hacer nuestro delicioso pan.")
                .url("vfsbhvgfvsabhkivasvbsvbsvc.com")
                .build();

        Video v2 = Video.builder()
                .titulo("Amasando")
                .orden(2)
                .descripcion("En este video amasaremos el pan y lo prepararemos para cocinarlo.")
                .url("ioyhjitjhnnbdvvbngjblkf.com")
                .build();

        Video v3 = Video.builder()
                .titulo("Horno")
                .orden(3)
                .descripcion("En este video daremos tips para hornear el pan a nuestro gusto.")
                .url("tjnfbnyjrhehekde.com")
                .build();

        Video v4 = Video.builder()
                .titulo("Introducción")
                .orden(1)
                .descripcion("Breve introducción donde repasaremos todos los apartados que iremos siguiendo durante el curso.")
                .url("ghklugfsbkjhkjthg.com")
                .build();

        Video v5 = Video.builder()
                .titulo("Relatividad general y teoría cuántica")
                .orden(2)
                .descripcion("Veremos como Einstein formuló la relatividad general dando forma al tiempo y la sintesis de esta con la teoría cuántica.")
                .url("uipoposdadfhghjj.com")
                .build();

        v.addToCurso(c);
        v2.addToCurso(c);
        v3.addToCurso(c);
        v4.addToCurso(c2);
        v5.addToCurso(c2);

        videoRepository.saveAll(List.of(v,v2,v3,v4,v5));


        profesorRepository.findAll()
                .forEach(profe -> {
                    System.out.println("\nProfe: "+ profe);
                    profe.getCursos().forEach(cursoOnline -> {
                        System.out.println("Curso: "+ cursoOnline);
                        cursoOnline.getVideos().forEach(System.out::println);
                    });
                });

        cursoOnlineRepository.delete(c2);
        System.out.println("\nBorramos el curso de física y debería borrarse de los cursos del profesor.");

        Optional<Profesor> profesor = profesorRepository.findById(p2.getId());
        System.out.println("Profe: "+profesor.get()+"\tCursos: "+profesor.get().getCursos());

        if(profesor.get().getCursos().isEmpty()){
            System.out.println("Todo correcto.");
        }

        System.out.println("\nPor otro lado, los vídeos deberían borrase también.");
        Optional<Video> video = videoRepository.findById(v4.getId());
        Optional<Video> video2 = videoRepository.findById(v5.getId());

        List.of(video, video2).forEach(vid -> {
            if(vid.isEmpty())
                System.out.println("Este vídeo no existe.");
        });


        profesorRepository.delete(p);
        System.out.println("\nBorramos al profesor Antonio y el profesor de su curso debería estar en null.");
        Optional<CursoOnline> cursoOnline = cursoOnlineRepository.findById(c.getId());

        System.out.println("Profesor del curso: "+cursoOnline.get().getProfesor());


    }
}
