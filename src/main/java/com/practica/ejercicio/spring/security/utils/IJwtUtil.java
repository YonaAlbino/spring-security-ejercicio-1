package com.practica.ejercicio.spring.security.utils;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface IJwtUtil {

        String createToken(Authentication authentication);
        DecodedJWT validateToken(String token);
        String extractUserName(DecodedJWT decodedJWT);
        Claim getSpecifClaim(DecodedJWT decodedJWT, String claimName);
        Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT);


}
