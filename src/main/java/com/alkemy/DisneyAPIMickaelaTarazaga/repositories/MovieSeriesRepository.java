
package com.alkemy.DisneyAPIMickaelaTarazaga.repositories;

import com.alkemy.DisneyAPIMickaelaTarazaga.entities.MovieSeriesEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSeriesRepository extends JpaRepository<MovieSeriesEntity, String>{
   
  Optional<MovieSeriesEntity> findById(String id);

    public List<MovieSeriesEntity> findByTitle(String title);

    public List<MovieSeriesEntity> findByGenresId(String genreId);
    
    public List<MovieSeriesEntity> findAllByOrderByCreationDateDesc();

    public List<MovieSeriesEntity> findAllByOrderByCreationDateAsc();

}