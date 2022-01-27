package com.alkemy.DisneyAPIMickaelaTarazaga.auth.repository;

import com.alkemy.DisneyAPIMickaelaTarazaga.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a UserEntity in the DB related to the username received
     *
     * @param username of the UserEntity to be found
     * @return The UserEntity if exists
     */
    UserEntity findByUsername(String username);
}
