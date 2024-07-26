package com.practica.ejercicio.spring.security.repository;

import com.practica.ejercicio.spring.security.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {
}
