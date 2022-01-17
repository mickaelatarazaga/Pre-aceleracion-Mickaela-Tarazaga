
package com.alkemy.DisneyAPIMickaelaTarazaga.repositories;

import com.alkemy.DisneyAPIMickaelaTarazaga.entities.MovieSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSeriesRepository extends JpaRepository<MovieSeriesEntity, String>{
   


}