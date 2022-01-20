package com.alkemy.DisneyAPIMickaelaTarazaga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Tag(name = "Auth")
@RestController
public class UserController {

//    private final UserService userService;
//
//    @Operation(description = "Register a new user")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Registration successful",content = @Content),
//            @ApiResponse(responseCode = "400", description = "Email already in use by another user", content = @Content)
//    })
//    @PostMapping("/auth/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto user) {
//
//        userService.saveUser(user);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//
//    }
//
//    @Operation(description = "User login")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Succesfull login",content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)) }),
//            @ApiResponse(responseCode = "403", description = "Unsuccesfull login", content = @Content)
//    })
//    @PostMapping("/auth/login")
//    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody UserDto user) {
//
//        return new ResponseEntity<>(new LoginResponse(userService.logInUser(user)), HttpStatus.OK);
//
//    }

}
