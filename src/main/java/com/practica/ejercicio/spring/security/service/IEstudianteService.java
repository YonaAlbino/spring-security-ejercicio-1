package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {
    public List<Estudiante> findAll();
    public Optional<Estudiante> findById(Long id);
    public void deleteById(Long id);
    public Estudiante save(Estudiante estudiante);
    public Estudiante update(Estudiante estudiante);
}
