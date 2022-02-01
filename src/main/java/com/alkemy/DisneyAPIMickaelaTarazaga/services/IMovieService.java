package com.alkemy.DisneyAPIMickaelaTarazaga.services;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.MovieDto;
import  com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;

import java.util.List;
import java.util.Set;

public interface IMovieService {

    List<Movie> getAll();

    List<Movie> findAllOrderByCreationDate(String order);

    Movie findById(Long movieId);

    List<Movie> findByTitle(String title);

    void delete(Long id);

    Movie save(Movie movie);

    List<Movie> findByGenreId(Long idGenre);

    Set<Genre> getGenres(Long id);

    void addGenres(Long movieId, List<Long> genresIds);

    void removeGenres(Long movieId, List<Long> genresIds);
    
    List<MovieDto> returnEmptyMovieDto();

}
