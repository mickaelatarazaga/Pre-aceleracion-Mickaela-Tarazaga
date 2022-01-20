package com.alkemy.DisneyAPIMickaelaTarazaga.controllers;

import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.CharacterDto;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.CharacterSlimDto;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.ListOfStringDto;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.MovieSlimDto;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.CharacterEntity;
import com.alkemy.DisneyAPIMickaelaTarazaga.exceptions.ErrorDetails;
import com.alkemy.DisneyAPIMickaelaTarazaga.mappers.MapStructMapper;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.ICharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Characters")
@RestController
@RequestMapping("/characters")
@SecurityRequirement(name = "bearerAuth")
public class CharacterController {

    private final MapStructMapper mapStructMapper;
    private final ICharacterService characterService;

    @Operation(description = "Gets all characters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All characters are shown", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterSlimDto.class)) })
    })
    @GetMapping()
    public ResponseEntity<List<CharacterSlimDto>> getAllCharacters() {

        return new ResponseEntity<>(mapStructMapper.charactersToCharacterSlimDtos(characterService.getAll()), HttpStatus.OK);

    }

    @Operation(description = "Finds a character by his ID and shows his details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDto.class)) }),
            @ApiResponse(responseCode = "404", description = "No character have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getCharacterById(@PathVariable("id") String id) {

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
    public ResponseEntity<List<CharacterDto>> findCharacterByMovieId(@Parameter(description = "Filter by MovieID") @RequestParam(value = "movie", required = false) String movieId) {

        return new ResponseEntity<>(mapStructMapper.charactersToCharacterDtos(characterService.findByMovieId(movieId)), HttpStatus.OK);

    }

    @Operation(description = "Deletes a character by his ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Character deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "No character with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCharacterById(@PathVariable("id") String id) {

        characterService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Operation(description = "Saves a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Character created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDto.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PostMapping()
    public ResponseEntity<CharacterDto> saveCharacter(@Valid @RequestBody CharacterDto character) {

        CharacterEntity characterCreated = characterService.save(mapStructMapper.characterDtoToCharacter(character));

        return new ResponseEntity<>(mapStructMapper.characterToCharacterDto(characterCreated), HttpStatus.CREATED);

    }

    @Operation(description = "Updates a character's info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDto.class)) }),
            @ApiResponse(responseCode = "404", description = "No character with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CharacterDto> updateCharacter(@Valid @RequestBody CharacterDto character, @PathVariable("id") String id) {

        CharacterEntity characterUpdated = characterService.save(mapStructMapper.updateCharacterFromDto(character, characterService.findById(id)));

        return new ResponseEntity<>(mapStructMapper.characterToCharacterDto(characterUpdated), HttpStatus.OK);

    }

    @Operation(description = "Shows all the movies of the character with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All movies of the character are shown", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterSlimDto.class)) }),
            @ApiResponse(responseCode = "404", description = "No character with the given ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) } )
    })
    @GetMapping("{id}/movies")
    public ResponseEntity<List<MovieSlimDto>> getCharacterMovies(@PathVariable("id") String characterId) {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieSlimDtos(new ArrayList<>(characterService.findById(characterId).getMovies())), HttpStatus.OK);

    }

    @Operation(description = "Given a list of MovieID's, adds all the corresponding movies to the character's movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movies added", content = @Content),
            @ApiResponse(responseCode = "404", description = "No character with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PutMapping("{id}/movies")
    public ResponseEntity<?> addMoviesToCharacter(@Valid @RequestBody ListOfStringDto moviesIds, @PathVariable("id") String characterId) {

        characterService.addMovies(characterId, moviesIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Operation(description = "Given a list of Movie ID's, removes all the corresponding movies from the character's movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movies removed", content = @Content),
            @ApiResponse(responseCode = "404", description = "No character with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @DeleteMapping("{id}/movies")
    public ResponseEntity<?> removeMoviesFromCharacter(@Valid @RequestBody ListOfStringDto moviesIds, @PathVariable("id") String characterId) {

        characterService.removeMovies(characterId, moviesIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
