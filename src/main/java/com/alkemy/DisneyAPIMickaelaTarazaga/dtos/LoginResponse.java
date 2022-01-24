package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class LoginResponse {

    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    
}
