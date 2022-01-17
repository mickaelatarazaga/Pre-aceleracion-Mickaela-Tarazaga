package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MovieSeriesDTO {

    
   private String id;
   private String title;
   private String image;
   private Date creationDate;
   private Integer rating;
   //private List<CharacterFilterDTO> characters;
   //private List<GenreFilterDTO> genres;

}
