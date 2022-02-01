package com.alkemy.DisneyAPIMickaelaTarazaga.auth.service;

import com.alkemy.DisneyAPIMickaelaTarazaga.auth.dto.AuthenticationRequest;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.dto.UserDTO;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.entity.UserEntity;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.repository.UserRepository;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    private UserRepository userRepository;
    private JwtUtils jwtTokenUtil;
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    public void setAttributes(
            UserRepository userRepository,
            JwtUtils jwtTokenUtil,
            @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    //SOBRESCRIBO LO QUE VIENE POR DEFECTO
    //CONFIGURO COMO VOY A IR A BUSCAR EL USUARIO
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) throw new UsernameNotFoundException("Username or password not found.");
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());// SI LO ENCUENTRO GENERO UN NUEVO USUARIO DE SPRING SECURITY QUE SE MANEJA EN EL FILTRO
    }

    //
    public boolean save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        UserEntity entitySaved = userRepository.save(userEntity);
        if (userEntity != null){emailService.sendWelcomeEmailTo(userEntity.getUsername());}
        return entitySaved != null;
    }

    /**
     * Receives an AuthenticationRequest and tries to authenticate it. If it can, returns the proper token.
     * @param authRequest
     * @return Token corresponding to the AuthenticationRequest
     * @throws Exception
     */
    public String signIn(AuthenticationRequest authRequest) throws Exception {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            userDetails = (UserDetails) auth.getPrincipal();
            return jwtTokenUtil.generateToken(userDetails);
        } catch (BadCredentialsException ex) {throw new Exception("Incorrect username or password.", ex);}
    }
}
