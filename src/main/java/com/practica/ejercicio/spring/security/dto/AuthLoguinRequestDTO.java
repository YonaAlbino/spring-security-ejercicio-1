package com.practica.ejercicio.spring.security.dto;
import jakarta.validation.constraints.NotBlank;

public record AuthLoguinRequestDTO(@NotBlank String username,
                                   @NotBlank String password) {
}
