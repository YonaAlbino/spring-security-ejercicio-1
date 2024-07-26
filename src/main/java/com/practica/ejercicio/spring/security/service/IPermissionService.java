package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    public List<Permission> findAll();
    public Optional<Permission> findById(Long id);
    public void deleteById (Long id);
    public Permission save (Permission permission);
    public Permission update(Permission permission);
}
