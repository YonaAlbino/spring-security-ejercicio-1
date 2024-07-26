package com.practica.ejercicio.spring.security.repository;

import com.practica.ejercicio.spring.security.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, Long> {
}
