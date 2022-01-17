package com.alkemy.DisneyAPIMickaelaTarazaga.controllers;

import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.MovieSeriesDTO;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.MovieSeriesEntity;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.IMovieSeriesService;
import com.alkemy.DisneyAPIMickaelaTarazaga.mappers.MapStructMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("movies")
public class MovieSeriesController {
    
    @Autowired
    private MapStructMapper mapStructMapper;
    
    @Autowired
    private IMovieSeriesService moviesSeriesService;
    
    @PostMapping
    public ResponseEntity <MovieSeriesDTO> saveMovie(@Valid @RequestBody MovieSeriesDTO movieSeriesDTO){
        
        MovieSeriesEntity movieSeriesCreated = moviesSeriesService.saved(mapStructMapper.movieDtoToMovie(movieSeriesDTO));
        
        return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieSeriesCreated), HttpStatus.CREATED );
    }  
    
        
}
