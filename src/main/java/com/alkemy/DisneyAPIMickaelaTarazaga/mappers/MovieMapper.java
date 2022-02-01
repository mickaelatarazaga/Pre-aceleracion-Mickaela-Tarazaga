package com.alkemy.DisneyAPIMickaelaTarazaga.mappers;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.Character;

import java.util.List;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MovieMapper  {

    MovieSlimDto movieToMovieSlimDto(Movie movie);

    MovieDto movieToMovieDto(Movie movie);

    Movie movieDtoToMovie(MovieDto movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Movie updateMovieFromDto(MovieDto movieDto, @MappingTarget Movie movie);

    List<MovieSlimDto> moviesToMovieSlimDtos(List<Movie> movies);

    List<MovieDto> moviesToMovieDtos(List<Movie> movies);

    List<GenreSlimDto> genresToGenreSlimDtos(List<Genre> genres);
}
