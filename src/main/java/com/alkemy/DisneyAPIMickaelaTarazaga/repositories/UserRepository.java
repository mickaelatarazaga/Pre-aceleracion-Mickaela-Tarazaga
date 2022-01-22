package com.alkemy.DisneyAPIMickaelaTarazaga.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

}