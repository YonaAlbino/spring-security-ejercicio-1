package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    public List<Curso> findAll();
    public Optional<Curso> findById(Long id);
    public void deleteById(Long id);
    public Curso save(Curso curso);
    public Curso update(Curso curso);
}
