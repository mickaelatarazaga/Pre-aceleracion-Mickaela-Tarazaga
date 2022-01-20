/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.DisneyAPIMickaelaTarazaga.services;

import com.alkemy.DisneyAPIMickaelaTarazaga.entities.GenreEntity;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.MovieSeriesEntity;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Mickaela Tarazaga
 */
public interface IMovieService {
    List<MovieSeriesEntity> getAll();

    List<MovieSeriesEntity> findAllOrderByCreationDate(String order);

    MovieSeriesEntity findById(String movieId);

    List<MovieSeriesEntity> findByTitle(String title);

    void delete(String id);

    MovieSeriesEntity save(MovieSeriesEntity movie);

    List<MovieSeriesEntity> findByGenreId(String idGenre);

    Set<GenreEntity> getGenres(String id);

    void addGenres(String movieId, List<String> genresIds);

    void removeGenres(String movieId, List<String> genresIds);
}
