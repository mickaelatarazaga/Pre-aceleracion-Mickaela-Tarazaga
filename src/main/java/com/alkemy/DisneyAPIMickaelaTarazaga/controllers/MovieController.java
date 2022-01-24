package com.alkemy.DisneyAPIMickaelaTarazaga.controllers;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.mappers.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.exceptions.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Movies")
@RestController
@RequestMapping("/movies")
@SecurityRequirement(name = "bearerAuth")
public class MovieController {

	@Autowired
    private MapStructMapper mapStructMapper;
	@Autowired
    private IMovieService movieService;

    
    @GetMapping()
    public ResponseEntity<List<MovieSlimDto>> getAllMovies() {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieSlimDtos(movieService.getAll()), HttpStatus.OK);

    }


    @GetMapping(params="order")
    public ResponseEntity<List<MovieDto>> getAllMoviesOrderByCreationDate(
            @Parameter(description = "Get all movies order by creation date (ASC | DESC)")
            @RequestParam(value ="order", required = false) String order) {

        List<Movie> movies = movieService.findAllOrderByCreationDate(order);

        if(movies == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            return new ResponseEntity<>(mapStructMapper.moviesToMovieDtos(movies), HttpStatus.OK);

        }

    }

    
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findMovieById(@PathVariable("id") Long movieId) {

        return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieService.findById(movieId)), HttpStatus.OK);

    }

    @GetMapping(params="title")
    public ResponseEntity<List<MovieDto>> findMovieByTitle(
            @Parameter(description = "Filter movies by title") @RequestParam(value = "title", required = false) String title) {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieDtos(movieService.findByTitle(title)), HttpStatus.OK);

    }

    @GetMapping(params="genre")
    public ResponseEntity<List<MovieDto>> findMovieByGenre(
            @Parameter(description = "Filter movies by genreID") @RequestParam(value = "genre", required = false) Long genreId) {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieDtos(movieService.findByGenreId(genreId)), HttpStatus.OK);

    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovieById(@PathVariable("id") Long id) {

        movieService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    
    @PostMapping()
    public ResponseEntity<MovieDto> saveMovie(@Valid @RequestBody MovieDto movie) {

        Movie movieCreated = movieService.save(mapStructMapper.movieDtoToMovie(movie));

        return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieCreated), HttpStatus.CREATED);

    }

   
    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieDto movie, @PathVariable("id") Long id){

        Movie movieUpdated = movieService.save(mapStructMapper.updateMovieFromDto(movie, movieService.findById(id)));

        return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieUpdated), HttpStatus.OK);

    }

    
    @GetMapping("{id}/genres")
    public ResponseEntity<List<GenreSlimDto>> getMovieGenres(@PathVariable("id") Long movieId) {

        return new ResponseEntity<>(mapStructMapper.genresToGenreSlimDtos(new ArrayList<>(movieService.getGenres(movieId))), HttpStatus.OK);

    }

    
    @PutMapping("{id}/genres")
    public ResponseEntity<?> addGenresToMovie(@Valid @RequestBody ListOfLongDto genresIds, @PathVariable("id") Long movieId) {

        movieService.addGenres(movieId, genresIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    
    @DeleteMapping("{id}/genres")
    public ResponseEntity<?> removeGenresFromMovie(@Valid @RequestBody ListOfLongDto genresIds, @PathVariable("id") Long movieId) {

        movieService.removeGenres(movieId, genresIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
