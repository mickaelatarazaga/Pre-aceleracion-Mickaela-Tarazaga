package com.alkemy.DisneyAPIMickaelaTarazaga.controllers;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.mappers.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.Character;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Tag(name = "Characters")
@RestController
@RequestMapping("/characters")
public class CharacterController {

	@Autowired
    private MapStructMapper mapStructMapper;
	@Autowired
    private  ICharacterService characterService;

    
    @GetMapping()
    public ResponseEntity<List<CharacterSlimDto>> getAllCharacters() {

        return new ResponseEntity<>(mapStructMapper.charactersToCharacterSlimDtos(characterService.getAll()), HttpStatus.OK);

    }

    
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getCharacterById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(mapStructMapper.characterToCharacterDto(characterService.findById(id)), HttpStatus.OK);

    }

    @GetMapping(params = "name")
    public ResponseEntity<List<CharacterDto>> findCharacterByName(@Parameter(description = "Filter by name") @RequestParam(value = "name", required = false) String name) {

        return new ResponseEntity<>(mapStructMapper.charactersToCharacterDtos(characterService.findByName(name)), HttpStatus.OK);

    }

    @GetMapping(params="age")
    public ResponseEntity<List<CharacterDto>> findCharacterByAge(@Parameter(description = "Filter by age") @RequestParam(value = "age", required = false) Integer age) {

        return new ResponseEntity<>(mapStructMapper.charactersToCharacterDtos(characterService.findByAge(age)), HttpStatus.OK);

    }

    @GetMapping(params="movie")
    public ResponseEntity<List<CharacterDto>> findCharacterByMovieId(@Parameter(description = "Filter by MovieID") @RequestParam(value = "movie", required = false) Long movieId) {

        return new ResponseEntity<>(mapStructMapper.charactersToCharacterDtos(characterService.findByMovieId(movieId)), HttpStatus.OK);

    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCharacterById(@PathVariable("id") Long id) {

        characterService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    
    @PostMapping()
    public ResponseEntity<CharacterDto> saveCharacter(@Valid @RequestBody CharacterDto character) {

        Character characterCreated = characterService.save(mapStructMapper.characterDtoToCharacter(character));

        return new ResponseEntity<>(mapStructMapper.characterToCharacterDto(characterCreated), HttpStatus.CREATED);

    }

    
    @PatchMapping("/{id}")
    public ResponseEntity<CharacterDto> updateCharacter(@Valid @RequestBody CharacterDto character, @PathVariable("id") Long id) {

        Character characterUpdated = characterService.save(mapStructMapper.updateCharacterFromDto(character, characterService.findById(id)));

        return new ResponseEntity<>(mapStructMapper.characterToCharacterDto(characterUpdated), HttpStatus.OK);

    }

    
    @GetMapping("{id}/movies")
    public ResponseEntity<List<MovieSlimDto>> getCharacterMovies(@PathVariable("id") Long characterId) {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieSlimDtos(new ArrayList<>(characterService.findById(characterId).getMovies())), HttpStatus.OK);

    }

    
    @PutMapping("{id}/movies")
    public ResponseEntity<?> addMoviesToCharacter(@Valid @RequestBody ListOfLongDto moviesIds, @PathVariable("id") Long characterId) {

        characterService.addMovies(characterId, moviesIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    
    @DeleteMapping("{id}/movies")
    public ResponseEntity<?> removeMoviesFromCharacter(@Valid @RequestBody ListOfLongDto moviesIds, @PathVariable("id") Long characterId) {

        characterService.removeMovies(characterId, moviesIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
