package com.practica.ejercicio.spring.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomre;

    @ManyToOne
    @JoinColumn(name = "idProfesor")
    private Profesor profesor;

    @ManyToMany(mappedBy = "cursoList")
    private Set<Estudiante> estudianteList;
}
