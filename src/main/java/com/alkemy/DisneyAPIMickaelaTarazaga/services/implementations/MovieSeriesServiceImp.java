package com.alkemy.DisneyAPIMickaelaTarazaga.services.implementations;
import com.alkemy.DisneyAPIMickaelaTarazaga.repositories.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.MovieSeriesEntity;
import com.alkemy.DisneyAPIMickaelaTarazaga.mappers.MapStructMapper;
import com.alkemy.DisneyAPIMickaelaTarazaga.services.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


@Transactional
@Service
public class MovieSeriesServiceImp implements IMovieSeriesService{

        
    @Autowired
    private MovieSeriesRepository movieRepository;
    
    @Override
    public MovieSeriesEntity saved (MovieSeriesEntity movieSeriesEntity){
        
        return movieRepository.save(movieSeriesEntity);
        

    }

}
