package com.alkemy.DisneyAPIMickaelaTarazaga.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;

public interface GenreRepository extends JpaRepository<GenreEntity, String> {
}