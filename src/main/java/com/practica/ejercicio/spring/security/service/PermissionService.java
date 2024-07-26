package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Permission;
import com.practica.ejercicio.spring.security.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService{

    @Autowired
    private IPermissionRepository repo;

    @Override
    public List<Permission> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return repo.save(permission);
    }

    @Override
    public Permission update(Permission permission) {
        return this.save(permission);
    }
}
