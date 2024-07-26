package com.practica.ejercicio.spring.security.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Profesores")
public class Profesor extends Persona{
    private String salario;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private Set<Curso> listCursos = new HashSet<>();
}
