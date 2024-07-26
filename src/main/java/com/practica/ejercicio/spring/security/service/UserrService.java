package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Userr;
import com.practica.ejercicio.spring.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserrService implements IUserrService{

    @Autowired
    private IUserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Userr> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Userr> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Userr save(Userr userr) {
        return repo.save(userr);
    }

    @Override
    public Userr update(Userr userr) {
        return  this.save(userr);
    }

    @Override
    public String encriptPassword(String password) {
        return  passwordEncoder.encode(password);
    }
}
