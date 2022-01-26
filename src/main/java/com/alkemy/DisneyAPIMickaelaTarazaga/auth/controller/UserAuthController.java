package com.alkemy.DisneyAPIMickaelaTarazaga.auth.controller;

import com.alkemy.DisneyAPIMickaelaTarazaga.auth.dto.AuthenticationRequest;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.dto.AuthenticationResponse;
import com.alkemy.DisneyAPIMickaelaTarazaga.auth.dto.UserDTO;
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

    // ATTRIBUTES
    private UserDetailsCustomService userDetailsCustomService;

    // CONSTRUCTOR
    @Autowired
    public UserAuthController(UserDetailsCustomService userDetailsCustomService) {
        this.userDetailsCustomService = userDetailsCustomService;
    }

    /**
     * Endpoint to save a new User receiving its new attribues by request body
     * @param dto
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO dto) {
        userDetailsCustomService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * EndPoint to sign in to the application.
     * @param authRequest
     * @return Returns the token corresponding to the details in the Authentication Request
     * @throws Exception
     */
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authRequest) throws Exception {
        final String jwt = userDetailsCustomService.signIn(authRequest);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
