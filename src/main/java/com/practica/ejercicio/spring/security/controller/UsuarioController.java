package com.practica.ejercicio.spring.security.controller;

import com.practica.ejercicio.spring.security.model.Role;
import com.practica.ejercicio.spring.security.model.Userr;
import com.practica.ejercicio.spring.security.service.IRoleService;
import com.practica.ejercicio.spring.security.service.IUserrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {

    @Autowired
    private IUserrService userrService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public ResponseEntity<List<Userr>> getAll(){
        List<Userr> listUser = userrService.findAll();
        return ResponseEntity.ok(listUser);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public ResponseEntity<Userr> findById(@PathVariable Long id){
        Optional<Userr> userSec = userrService.findById(id);
        return  userSec.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Userr> save(@RequestBody Userr userr){
        Set<Role> roles = new HashSet<>();
        Role roleRead;

        userr.setPassword(userrService.encriptPassword(userr.getPassword()));

        for (Role rol : userr.getRoleList()){
            roleRead = roleService.findById(rol.getId()).orElse(null);

            if(roleRead != null)
                roles.add(roleRead);
        }

        if(!roles.isEmpty()){
            userr.setRoleList(roles);
            Userr userr1 = userrService.save(userr);
            return ResponseEntity.ok(userr1);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userrService.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado");
    }

}
