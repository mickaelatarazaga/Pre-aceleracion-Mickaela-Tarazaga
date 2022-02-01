package com.alkemy.DisneyAPIMickaelaTarazaga.services.implementations;
import com.alkemy.DisneyAPIMickaelaTarazaga.repositories.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.ICharacterService;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.Character;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.Movie;
import com.alkemy.DisneyAPIMickaelaTarazaga.exceptions.InvalidException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CharacterService implements ICharacterService {

    private CharacterRepository characterRepository;
    private MovieRepository movieRepository;

    @Override
    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character findById(Long characterId) {
        return characterRepository.findById(characterId).orElseThrow(() -> new InvalidException("No Character with ID : " + characterId));
    }

    @Override
    public List<Character> findByName(String name) {
        return characterRepository.findByName(name);
    }

    @Override
    public List<Character> findByAge(Integer age) {
        return characterRepository.findByAge(age);
    }

    @Override
    public void delete(Long id){
        characterRepository.delete(findById(id));
    }

    @Override
    public Character save(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public List<Character> findByMovieId(Long idMovie) {
        return characterRepository.findByMoviesId(idMovie);
    }

    private boolean checkMoviesExistence(List<Long> moviesIds) {
        return movieRepository.findAll().stream().map(Movie::getId).collect(Collectors.toList()).containsAll(moviesIds);
    }

    @Override
    public void addMovies(Long characterId, List<Long> moviesIds) {
        Character character = findById(characterId);
        if (checkMoviesExistence(moviesIds)) {movieRepository.findAllById(moviesIds).forEach(movie -> character.getMovies().add(movie));
        } else {throw new InvalidException("Make sure all movies you want to add to the character already exist on the server");}
        characterRepository.save(character);
    }

    @Override
    public void removeMovies(Long characterId, List<Long> moviesIds) {
        Character character = findById(characterId);
        character.getMovies().removeIf(movie -> moviesIds.contains(movie.getId()));
        characterRepository.save(character);
    }
}
