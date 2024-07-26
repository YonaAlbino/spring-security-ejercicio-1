package com.practica.ejercicio.spring.security.controller;

import com.practica.ejercicio.spring.security.model.Curso;
import com.practica.ejercicio.spring.security.model.Profesor;
import com.practica.ejercicio.spring.security.service.ICursoService;
import com.practica.ejercicio.spring.security.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/profesor")
@PreAuthorize("denyAll()")
public class ProfesorController {

    @Autowired
    private IProfesorService profeService;
    @Autowired
    private ICursoService cursoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public ResponseEntity<List<Profesor>> getAll(){
        return ResponseEntity.ok(profeService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public ResponseEntity<Profesor> findById(@PathVariable Long id){
        Optional<Profesor> profesor = profeService.findById(id);
        return profesor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Profesor> save(@RequestBody Profesor profesor){
        Optional<Curso> cursoRead;
        Set<Curso> cursoList = new HashSet<>();

        for (Curso curso : profesor.getListCursos()){
            cursoRead = cursoService.findById(curso.getId());

            if(cursoRead != null)
                cursoList.add(cursoRead.get());
        }

        profesor.setListCursos(cursoList);
        return ResponseEntity.ok(profeService.save(profesor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delte(@PathVariable Long id){
        profeService.deleteById(id);
        return ResponseEntity.ok("Profesor eliminado");
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Profesor> update(@RequestBody Profesor profesor){
        Optional<Curso> cursoRead;
        Set<Curso> cursoList = new HashSet<>();

        for (Curso curso : profesor.getListCursos()){
            cursoRead = cursoService.findById(curso.getId());

            if(cursoRead != null)
                cursoList.add(cursoRead.get());
        }

        profesor.setListCursos(cursoList);
        return ResponseEntity.ok(profeService.update(profesor));
    }

}
