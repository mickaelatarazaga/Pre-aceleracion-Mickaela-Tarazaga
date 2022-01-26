package com.alkemy.DisneyAPIMickaelaTarazaga.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    //ATTRIBUTES
    @Email(message = "Username must be an email.")
    private String username;
    @Size(min = 8)
    private String password;
}
