package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface IProfesorService {
    public List<Profesor> findAll();
    public Optional<Profesor> findById(Long id);
    public void deleteById (Long id);
    public Profesor save (Profesor profesor);
    public Profesor update(Profesor profesor);
}
