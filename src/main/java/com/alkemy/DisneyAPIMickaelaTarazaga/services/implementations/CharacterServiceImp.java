package com.alkemy.DisneyAPIMickaelaTarazaga.services.implementations;

import com.alkemy.DisneyAPIMickaelaTarazaga.services.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.repositories.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alkemy.DisneyAPIMickaelaTarazaga.repositories.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CharacterServiceImp implements ICharacterService{

    private final CharacterRepository characterRepository;
    private final MovieSeriesRepository movieRepository;

    @Override
    public List<CharacterEntity> getAll() {

        return characterRepository.findAll();

    }

    @Override
    public CharacterEntity findById(String characterId) {

        return characterRepository.findById(characterId).orElseThrow(() -> new ResourceNotFoundException("No Character with ID : " + characterId));

    }

    @Override
    public List<CharacterEntity> findByName(String name) {

        return characterRepository.findByName(name);

    }

    @Override
    public List<CharacterEntity> findByAge(Integer age) {

        return characterRepository.findByAge(age);

    }

    @Override
    public void delete(String id){

        characterRepository.delete(findById(id));

    }

    @Override
    public CharacterEntity save(CharacterEntity character) {

        return characterRepository.save(character);

    }

    @Override
    public List<CharacterEntity> findByMovieId(String idMovie) {

        return characterRepository.findByMoviesId(idMovie);

    }

    private boolean checkMoviesExistence(List<String> moviesIds) {

        return movieRepository.findAll().stream().map(MovieSeriesEntity::getId).collect(Collectors.toList()).containsAll(moviesIds);

    }

    @Override
    public void addMovies(String characterId, List<String> moviesIds) {

        CharacterEntity character = findById(characterId);

        if (checkMoviesExistence(moviesIds)) {

            movieRepository.findAllById(moviesIds).forEach(movie -> character.getMovies().add(movie));

        } else {

            throw new ResourceNotFoundException("Make sure all movies you want to add to the character already exist on the server");

        }

        characterRepository.save(character);

    }

    @Override
    public void removeMovies(String characterId, List<String> moviesIds) {

        CharacterEntity character = findById(characterId);

        character.getMovies().removeIf(movie -> moviesIds.contains(movie.getId()));

        characterRepository.save(character);

    }

}
