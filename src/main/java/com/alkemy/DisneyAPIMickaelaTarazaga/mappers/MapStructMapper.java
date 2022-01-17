package com.alkemy.DisneyAPIMickaelaTarazaga.mappers;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MapStructMapper  {
  
    MovieSeriesDTO movieToMovieDto(MovieSeriesEntity movie);

    MovieSeriesEntity movieDtoToMovie(MovieSeriesDTO movie);
    

}
