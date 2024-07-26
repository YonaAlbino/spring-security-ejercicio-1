package com.practica.ejercicio.spring.security.controller;

import com.practica.ejercicio.spring.security.model.Curso;
import com.practica.ejercicio.spring.security.model.Estudiante;
import com.practica.ejercicio.spring.security.service.ICursoService;
import com.practica.ejercicio.spring.security.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/estudiante")
@PreAuthorize("denyAll()")
public class EstudianteController {

    @Autowired
    private IEstudianteService estudianteService;

    @Autowired
    private ICursoService cursoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public ResponseEntity<List<Estudiante>> getAll(){
        return ResponseEntity.ok(estudianteService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public ResponseEntity<Estudiante> findById(@PathVariable Long id){
        Optional<Estudiante> estudiante = estudianteService.findById(id);
        return estudiante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Estudiante> save(@RequestBody Estudiante estudiante){
        Optional<Curso> cursoRead;
        Set<Curso> cursosList = new HashSet<>();

        for(Curso curs : estudiante.getCursoList()){
            cursoRead = cursoService.findById(curs.getId());

            if(cursoRead != null){
                cursosList.add(cursoRead.get());
            }
        }

        //Estudiante estudiante1 = new Estudiante();
        estudiante.setCursoList(cursosList);
        return ResponseEntity.ok(estudianteService.save(estudiante));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        estudianteService.deleteById(id);
        return ResponseEntity.ok("Estudiante eliminado");
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Estudiante> update(@RequestBody Estudiante estudiante){
        Optional<Curso> cursoRead;
        Set<Curso> cursosList = new HashSet<>();

        for(Curso curs : estudiante.getCursoList()){
            cursoRead = cursoService.findById(curs.getId());

            if(cursoRead != null){
                cursosList.add(cursoRead.get());
            }
        }

        estudiante.setCursoList(cursosList);
        return ResponseEntity.ok(estudianteService.update(estudiante));
    }

}
