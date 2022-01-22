package com.alkemy.DisneyAPIMickaelaTarazaga.services;

import com.alkemy.DisneyAPIMickaelaTarazaga.entities.CharacterEntity;
import java.util.List;

public interface ICharacterService {

    List<CharacterEntity> getAll();

    CharacterEntity findById(String characterId);

    List<CharacterEntity> findByName(String name);

    List<CharacterEntity> findByAge(Integer age);

    void delete(String id);

    CharacterEntity save(CharacterEntity character);

    List<CharacterEntity> findByMovieId(String idMovie);

    void addMovies(String characterId, List<String> moviesIds);

    void removeMovies(String characterId, List<String> moviesIds);

}
