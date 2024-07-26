package com.practica.ejercicio.spring.security.repository;

import com.practica.ejercicio.spring.security.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Userr, Long> {

    Optional<Userr> findUserEntityByUserName(String username);

}
