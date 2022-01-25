package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MovieSlimDto {

    private Long id;
    private String image;
    private String title;
    private LocalDateTime creationDate;

   
    
}
