package com.alkemy.DisneyAPIMickaelaTarazaga.auth.service;

import com.alkemy.DisneyAPIMickaelaTarazaga.auth.repository.UserRepository;
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

@Service
public class UserDetailsCustomService implements UserDetailsService {
//
//    // ATTRIBUTES
//    private UserRepository userRepository;
//    private JwtUtils jwtTokenUtil;
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    public void setAttributes(
//            UserRepository userRepository,
//            JwtUtils jwtTokenUtil,
//            @Lazy AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.authenticationManager = authenticationManager;
//    }
//
//    /**
//     * Overridden abstract method from UserDetailsService
//     * Looks for an UserEntity in the DB and, if there is such, returns a new User with its attributes
//     *
//     * @param username To look for in DB
//     * @return A new Object User with its attributes and no roles
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        UserEntity userEntity = userRepository.findByUsername(username);
//        if (userEntity == null)
//            throw new UsernameNotFoundException("Username or password not found.");
//        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
//    }
//
//    /**
//     * Turns the UserDTO parameter into an Entity and saves it to the DB
//     * Additionally, sends a welcome email to the User e-mail (not supported yet)
//     *
//     * @param userDTO to be persisted
//     * @return true if it is not null
//     */
//    public boolean save(UserDTO userDTO) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(userDTO.getUsername());
//        userEntity.setPassword(userDTO.getPassword());
//        UserEntity entitySaved = userRepository.save(userEntity);
//        if (userEntity != null)
//            emailService.sendWelcomeEmailTo(userEntity.getUsername());
//        return entitySaved != null;
//    }
//
//    /**
//     * Receives an AuthenticationRequest and tries to authenticate it. If it can, returns the proper token.
//     * @param authRequest
//     * @return Token corresponding to the AuthenticationRequest
//     * @throws Exception
//     */
//    public String signIn(AuthenticationRequest authRequest) throws Exception {
//
//        UserDetails userDetails;
//        try {
//            Authentication auth = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//            );
//            userDetails = (UserDetails) auth.getPrincipal();
//            return jwtTokenUtil.generateToken(userDetails);
//        } catch (BadCredentialsException ex) {
//            throw new Exception("Incorrect username or password.", ex);
//        }
//    }
}
