package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email must have a valid format")
    private String email;

    private String password;
  
    
}
