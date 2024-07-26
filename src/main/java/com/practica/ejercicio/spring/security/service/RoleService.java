package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Role;
import com.practica.ejercicio.spring.security.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository repo;

    @Override
    public List<Role> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Role save(Role role) {
        return repo.save(role);
    }

    @Override
    public Role update(Role role) {
        return this.save(role);
    }
}
