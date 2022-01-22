package com.alkemy.DisneyAPIMickaelaTarazaga.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<CharacterEntity, String> {

    Optional<CharacterEntity> findById(String id);

    List<CharacterEntity> findByName(String name);

    List<CharacterEntity> findByAge(Integer age);

    List<CharacterEntity> findByMoviesId(String id);

}