package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Estudiante;
import com.practica.ejercicio.spring.security.repository.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService implements IEstudianteService{

    @Autowired
    private IEstudianteRepository estudianteRepo;

    @Override
    public List<Estudiante> findAll() {
        return estudianteRepo.findAll();
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
         return estudianteRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        estudianteRepo.deleteById(id);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return estudianteRepo.save(estudiante);
    }

    @Override
    public Estudiante update(Estudiante estudiante) {
        return this.save(estudiante);
    }
}
