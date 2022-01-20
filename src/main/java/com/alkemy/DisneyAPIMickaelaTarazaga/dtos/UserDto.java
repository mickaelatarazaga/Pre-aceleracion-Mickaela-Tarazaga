package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {

    @Email(message = "Email must have a valid format")
    private String email;

    private String password;

    private String id;
   
    private String firstName;
    
    private String lastName;
    
    
}
