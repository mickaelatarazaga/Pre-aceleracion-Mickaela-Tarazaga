package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieSlimDto {

    private String id;
    private String image;
    private String title;
    private LocalDateTime creationDate;

}
