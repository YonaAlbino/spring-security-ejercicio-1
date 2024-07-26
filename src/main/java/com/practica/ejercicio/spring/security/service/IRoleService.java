package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    public List<Role> findAll();
    public Optional<Role> findById(Long id);
    public void deleteById (Long id);
    public Role save (Role role);
    public Role update(Role role);
}
