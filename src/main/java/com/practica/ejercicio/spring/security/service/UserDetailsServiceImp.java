package com.practica.ejercicio.spring.security.service;

import com.practica.ejercicio.spring.security.dto.AuthLoguinRequestDTO;
import com.practica.ejercicio.spring.security.dto.AuthLoguinResponseDTO;
import com.practica.ejercicio.spring.security.model.Userr;
import com.practica.ejercicio.spring.security.repository.IUserRepository;
import com.practica.ejercicio.spring.security.utils.IJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IJwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Userr userr = repo.findUserEntityByUserName(username).orElseThrow(
                () ->new UsernameNotFoundException("El usuarion " + username +" no fue encontrado"));


        List<GrantedAuthority> authorityList = new ArrayList<>();

        userr.getRoleList()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));

        userr.getRoleList().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        return new User(userr.getUserName(),
                userr.getPassword(),
                userr.isEnable(),
                userr.isAccountNotExpired(),
                userr.isAccountNotLocked(),
                userr.isCredentialNotExpired(),
                authorityList);
    }


    public AuthLoguinResponseDTO loguin(AuthLoguinRequestDTO authLoguinRequestDTO){
        String username = authLoguinRequestDTO.username();
        String password = authLoguinRequestDTO.password();

        Authentication authentication = this.authentication(username,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accesToken = jwtUtil.createToken(authentication);
        AuthLoguinResponseDTO authLoguinResponseDTO = new AuthLoguinResponseDTO(authentication.getName()," Loguin exitoso", accesToken, true);
        return authLoguinResponseDTO;
    }

    public Authentication authentication(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null)
            throw new BadCredentialsException("Nombre de usuario incorrecto");

        if(!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Contraseña incorrecta");

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
