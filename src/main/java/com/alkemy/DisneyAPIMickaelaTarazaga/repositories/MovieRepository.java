package com.alkemy.DisneyAPIMickaelaTarazaga.repositories;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findById(Long id);

    List<Movie> findByTitle(String title);

    List<Movie> findByGenresId(Long genreId);

    List<Movie> findAllByOrderByCreationDateAsc();

    List<Movie> findAllByOrderByCreationDateDesc();

}