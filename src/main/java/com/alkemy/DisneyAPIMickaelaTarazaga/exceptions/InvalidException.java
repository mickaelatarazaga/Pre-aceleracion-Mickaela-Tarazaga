package com.alkemy.DisneyAPIMickaelaTarazaga.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InvalidException extends RuntimeException {
    private String message;

}
