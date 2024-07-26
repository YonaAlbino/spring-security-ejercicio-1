package com.practica.ejercicio.spring.security.controller;

import com.practica.ejercicio.spring.security.model.Curso;
import com.practica.ejercicio.spring.security.model.Profesor;
import com.practica.ejercicio.spring.security.service.ICursoService;
import com.practica.ejercicio.spring.security.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/curso")
@PreAuthorize("denyAll()")
public class CursoController {

    @Autowired
    private ICursoService cursoService;

    @Autowired
    private IProfesorService profesorService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ESTUDIANTE', 'PROFESOR')")
    public ResponseEntity<List<Curso>> findAll(){
        return  ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ESTUDIANTE', 'PROFESOR')")
    public ResponseEntity<Curso> findById(@PathVariable Long id){
        Optional<Curso> curso = cursoService.findById(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Curso> saveCurso(@RequestBody Curso curso){
        return ResponseEntity.ok(cursoService.save(curso));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCurso(@PathVariable Long id){
        cursoService.deleteById(id);
        return ResponseEntity.ok("Curso eliminado");
    }

    @PutMapping("/{id_profesor}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public ResponseEntity<Curso> editCurso(@RequestBody Curso curso, @PathVariable Long id_profesor){
        Optional<Profesor> profesor = profesorService.findById(id_profesor);

        if(profesor.isPresent()){
            if(profesor.get().getId() == curso.getProfesor().getId())
                return ResponseEntity.ok(cursoService.update(curso));
            else return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.notFound().build();
    }
}
