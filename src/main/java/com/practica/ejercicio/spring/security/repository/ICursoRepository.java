package com.practica.ejercicio.spring.security.repository;

import com.practica.ejercicio.spring.security.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {
}
