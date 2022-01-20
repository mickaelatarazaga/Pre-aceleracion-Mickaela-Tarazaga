/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.DisneyAPIMickaelaTarazaga.services.implementations;

import com.alkemy.DisneyAPIMickaelaTarazaga.entities.GenreEntity;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.MovieSeriesEntity;
import com.alkemy.DisneyAPIMickaelaTarazaga.exceptions.ResourceNotFoundException;
import com.alkemy.DisneyAPIMickaelaTarazaga.repositories.GenreRepository;
import com.alkemy.DisneyAPIMickaelaTarazaga.repositories.MovieSeriesRepository;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.IMovieService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mickaela Tarazaga
 */
@RequiredArgsConstructor
@Transactional
@Service
public class MovieServiceImp implements IMovieService{
    


    private final MovieSeriesRepository movieRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<MovieSeriesEntity> getAll() {

        return movieRepository.findAll();

    }

    @Override
    public List<MovieSeriesEntity> findAllOrderByCreationDate(String order) {

        if(order.equalsIgnoreCase("ASC")) {

            return movieRepository.findAllByOrderByCreationDateAsc();

        } else if (order.equalsIgnoreCase("DESC")) {

            return movieRepository.findAllByOrderByCreationDateDesc();

        }

        return null;

    }

    @Override
    public MovieSeriesEntity findById(String movieId) {

        return movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("No Movie with ID : " + movieId));

    }

    @Override
    public List<MovieSeriesEntity> findByTitle(String title) {

        return movieRepository.findByTitle(title);

    }

    @Override
    public void delete(String id){

        movieRepository.delete(findById(id));

    }

    @Override
    public MovieSeriesEntity save(MovieSeriesEntity movie) {


        return movieRepository.save(movie);

    }

    @Override
    public List<MovieSeriesEntity> findByGenreId(String idGenre) {

        return movieRepository.findByGenresId(idGenre);

    }

    private boolean checkGenresExistence(List<String> genresIds) {

        return genreRepository.findAll().stream().map(GenreEntity::getId).collect(Collectors.toList()).containsAll(genresIds);

    }

    @Override
    public Set<GenreEntity> getGenres(String id) {

        return findById(id).getGenres();

    }

    @Override
    public void addGenres(String movieId, List<String> genresIds) {

        MovieSeriesEntity movie = findById(movieId);

        if (checkGenresExistence(genresIds)) {

            genreRepository.findAllById(genresIds).forEach(genre -> movie.getGenres().add(genre));

        } else {

            throw new ResourceNotFoundException("Make sure all movies you want to add to the character already exist on the server");

        }

        movieRepository.save(movie);

    }

    @Override
    public void removeGenres(String movieId, List<String> genresIds) {

        MovieSeriesEntity movie = findById(movieId);

        movie.getGenres().removeIf(genre -> genresIds.contains(genre.getId()));

        movieRepository.save(movie);

    }

}
