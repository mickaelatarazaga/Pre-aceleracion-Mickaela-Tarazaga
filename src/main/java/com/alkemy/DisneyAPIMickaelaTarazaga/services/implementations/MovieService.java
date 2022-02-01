package com.alkemy.DisneyAPIMickaelaTarazaga.services.implementations;


import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.MovieDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.DisneyAPIMickaelaTarazaga.repositories.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.exceptions.InvalidException;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;

@RequiredArgsConstructor
@Transactional
@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findAllOrderByCreationDate(String order) {
        if(order.equalsIgnoreCase("ASC")) {return movieRepository.findAllByOrderByCreationDateAsc();
        } else if (order.equalsIgnoreCase("DESC")) {
            return movieRepository.findAllByOrderByCreationDateDesc();
        }
        return null;
    }

    @Override
    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new InvalidException("No Movie with ID : " + movieId));
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public void delete(Long id){
        movieRepository.delete(findById(id));
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findByGenreId(Long idGenre) {
        return movieRepository.findByGenresId(idGenre);
    }

    private boolean checkGenresExistence(List<Long> genresIds) {
        return genreRepository.findAll().stream().map(Genre::getId).collect(Collectors.toList()).containsAll(genresIds);
    }

    @Override
    public Set<Genre> getGenres(Long id) {
        return findById(id).getGenres();
    }

    @Override
    public void addGenres(Long movieId, List<Long> genresIds) {
        Movie movie = findById(movieId);
        if (checkGenresExistence(genresIds)) {
            genreRepository.findAllById(genresIds).forEach(genre -> movie.getGenres().add(genre));
        } else {
            throw new InvalidException("Make sure all movies you want to add to the character already exist on the server");
        }
        movieRepository.save(movie);
    }

    @Override
    public void removeGenres(Long movieId, List<Long> genresIds) {
        Movie movie = findById(movieId);
        movie.getGenres().removeIf(genre -> genresIds.contains(genre.getId()));
        movieRepository.save(movie);
    }
    
    @Override
    public List<MovieDto> returnEmptyMovieDto() {
        List<MovieDto> emptyMovies = new ArrayList<MovieDto>();
        return emptyMovies;
    }

}
