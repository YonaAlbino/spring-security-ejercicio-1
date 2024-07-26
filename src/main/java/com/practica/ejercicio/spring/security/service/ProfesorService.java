package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Profesor;
import com.practica.ejercicio.spring.security.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService implements IProfesorService{

    @Autowired
    private IProfesorRepository profeRepo;

    @Override
    public List<Profesor> findAll() {
        return profeRepo.findAll();
    }

    @Override
    public Optional<Profesor> findById(Long id) {
        return profeRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        profeRepo.deleteById(id);
    }

    @Override
    public Profesor save(Profesor profesor) {
        return profeRepo.save(profesor);
    }

    @Override
    public Profesor update(Profesor profesor) {
        return this.save(profesor);
    }
}
