package com.alkemy.DisneyAPIMickaelaTarazaga.auth.controller;

import com.alkemy.DisneyAPIMickaelaTarazaga.auth.dto.AuthenticationRequest;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.dto.AuthenticationResponse;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.dto.UserDTO;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.service.JwtUtils;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private UserDetailsCustomService userDetailsCustomService;
      

    @Autowired 
    public UserAuthController(UserDetailsCustomService userDetailsCustomService){ 
        this.userDetailsCustomService = userDetailsCustomService; }
 
    @PostMapping("/signup") 
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO dto) { 
        userDetailsCustomService.save(dto); 
        return ResponseEntity.status(HttpStatus.CREATED).build(); }

    @PostMapping("/signin") 
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authRequest) throws Exception {
        final String jwt = userDetailsCustomService.signIn(authRequest); 
        return ResponseEntity.ok(new AuthenticationResponse(jwt)); }
}
