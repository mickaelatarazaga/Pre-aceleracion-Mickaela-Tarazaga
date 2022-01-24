package com.alkemy.DisneyAPIMickaelaTarazaga.repositories;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.Character;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    Optional<Character> findById(Long id);

    List<Character> findByName(String name);

    List<Character> findByAge(Integer age);

    List<Character> findByMoviesId(Long id);

    

}