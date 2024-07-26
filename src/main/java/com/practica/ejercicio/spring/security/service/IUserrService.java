package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Userr;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IUserrService {
    public List<Userr> findAll();
    public Optional<Userr> findById(Long id);
    public void deleteById (Long id);
    public Userr save (Userr userr);
    public Userr update(Userr userr);
    public String encriptPassword(String password);
}
