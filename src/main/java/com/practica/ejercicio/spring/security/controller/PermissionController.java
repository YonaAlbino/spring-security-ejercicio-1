package com.practica.ejercicio.spring.security.controller;

import com.practica.ejercicio.spring.security.model.Permission;
import com.practica.ejercicio.spring.security.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController {

    @Autowired
    private IPermissionService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public ResponseEntity<List<Permission>> getAllPermissions(){
       return  ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id){
        Optional<Permission> permisiion = service.findById(id);
        return permisiion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission){
        return ResponseEntity.ok(service.save(permission));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok("Permiso eliminado");
    }

}
