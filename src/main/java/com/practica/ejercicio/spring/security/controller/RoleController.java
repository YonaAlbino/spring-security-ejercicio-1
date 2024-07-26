package com.practica.ejercicio.spring.security.controller;

import com.practica.ejercicio.spring.security.model.Permission;
import com.practica.ejercicio.spring.security.model.Role;
import com.practica.ejercicio.spring.security.service.IPermissionService;
import com.practica.ejercicio.spring.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    @Autowired
    private IRoleService roleservice;

    @Autowired
    private IPermissionService permisionService;

    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
        return ResponseEntity.ok(roleservice.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public ResponseEntity<Role> findRole(@PathVariable Long id){
        Optional<Role> rol = roleservice.findById(id);
        return rol.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> createRol(@RequestBody Role role){
        Set<Permission> permissionsList = new HashSet<>();
        Permission permission;

        for (Permission per : role.getPermissionList()){
            permission = permisionService.findById(per.getId()).orElse(null);

            if(permission != null)
                permissionsList.add(permission);
        }

        role.setPermissionList(permissionsList);
        return ResponseEntity.ok(roleservice.save(role));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRol(@PathVariable Long id){
        roleservice.deleteById(id);
        return ResponseEntity.ok("Rol eliminado");
    }

}
