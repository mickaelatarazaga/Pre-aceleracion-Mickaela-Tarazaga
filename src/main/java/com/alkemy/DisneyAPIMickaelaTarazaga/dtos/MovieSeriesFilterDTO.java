package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSeriesFilterDTO {
    
    private String id;
    private String image;
    private String title;
    private Date creationDate;
    
}
