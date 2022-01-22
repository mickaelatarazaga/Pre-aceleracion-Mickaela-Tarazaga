package com.alkemy.DisneyAPIMickaelaTarazaga.controllers;

import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.MovieDto;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.MovieSeriesEntity;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.mappers.MapStructMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.exceptions.ErrorDetails;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Movies")
@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieSeriesController {
    
   
    private final MapStructMapper mapStructMapper;
    
    private final IMovieService movieService;
        

    
    @GetMapping()
    public ResponseEntity<List<MovieSlimDto>> getAllMovies() {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieSlimDtos(movieService.getAll()), HttpStatus.OK);

    }


    @GetMapping(params="order")
    public ResponseEntity<List<MovieDto>> getAllMoviesOrderByCreationDate(
            @Parameter(description = "Get all movies order by creation date (ASC | DESC)")
            @RequestParam(value ="order", required = false) String order) {

        List<MovieSeriesEntity> movies = movieService.findAllOrderByCreationDate(order);

        if(movies == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            return new ResponseEntity<>(mapStructMapper.moviesToMovieDtos(movies), HttpStatus.OK);

        }

    }

    
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findMovieById(@PathVariable("id") String movieId) {

        return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieService.findById(movieId)), HttpStatus.OK);

    }

    @GetMapping(params="title")
    public ResponseEntity<List<MovieDto>> findMovieByTitle(
            @Parameter(description = "Filter movies by title") @RequestParam(value = "title", required = false) String title) {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieDtos(movieService.findByTitle(title)), HttpStatus.OK);

    }

    @GetMapping(params="genre")
    public ResponseEntity<List<MovieDto>> findMovieByGenre(
            @Parameter(description = "Filter movies by genreID") @RequestParam(value = "genre", required = false) String genreId) {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieDtos(movieService.findByGenreId(genreId)), HttpStatus.OK);

    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovieById(@PathVariable("id") String id) {

        movieService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping()
    public ResponseEntity<MovieDto> saveMovie(@Valid @RequestBody MovieDto movie) {

        MovieSeriesEntity movieCreated = movieService.save(mapStructMapper.movieDtoToMovie(movie));

        return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieCreated), HttpStatus.CREATED);

    }
    

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@Valid @org.springframework.web.bind.annotation.RequestBody MovieDto movie, @PathVariable("id") String id){

        MovieSeriesEntity movieUpdated = movieService.save(mapStructMapper.updateMovieFromDto(movie, movieService.findById(id)));

        return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieUpdated), HttpStatus.OK);

    }

   
    @GetMapping("{id}/genres")
    public ResponseEntity<List<GenreSlimDto>> getMovieGenres(@PathVariable("id") String movieId) {

        return new ResponseEntity<>(mapStructMapper.genresToGenreSlimDtos(new ArrayList<>(movieService.getGenres(movieId))), HttpStatus.OK);

    }

    
    @PutMapping("{id}/genres")
    public ResponseEntity<?> addGenresToMovie(@Valid @org.springframework.web.bind.annotation.RequestBody ListOfStringDto genresIds, @PathVariable("id") String movieId) {

        movieService.addGenres(movieId, genresIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    
    @DeleteMapping("{id}/genres")
    public ResponseEntity<?> removeGenresFromMovie(@Valid @org.springframework.web.bind.annotation.RequestBody ListOfStringDto genresIds, @PathVariable("id") String movieId) {

        movieService.removeGenres(movieId, genresIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

        
}
