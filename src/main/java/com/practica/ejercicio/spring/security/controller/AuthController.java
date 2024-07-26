package com.practica.ejercicio.spring.security.controller;

import com.practica.ejercicio.spring.security.dto.AuthLoguinRequestDTO;
import com.practica.ejercicio.spring.security.dto.AuthLoguinResponseDTO;
import com.practica.ejercicio.spring.security.service.IUserrService;
import com.practica.ejercicio.spring.security.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @PostMapping("/loguin")
    public ResponseEntity<AuthLoguinResponseDTO> loguin(@RequestBody AuthLoguinRequestDTO authLoguinRequestDTO){
        return  ResponseEntity.ok(userDetailsServiceImp.loguin(authLoguinRequestDTO));
    }
}
