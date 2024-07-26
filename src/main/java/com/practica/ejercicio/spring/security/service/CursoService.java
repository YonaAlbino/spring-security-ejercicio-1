package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Curso;
import com.practica.ejercicio.spring.security.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService implements ICursoService{

    @Autowired
    private ICursoRepository repoCurso;

    @Override
    public List<Curso> findAll() {
        return repoCurso.findAll();
    }

    @Override
    public Optional<Curso> findById(Long id) {
        return repoCurso.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repoCurso.deleteById(id);
    }

    @Override
    public Curso save(Curso curso) {
       return repoCurso.save(curso);
    }

    @Override
    public Curso update(Curso curso) {
        return this.save(curso);
    }
}
