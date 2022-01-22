package com.alkemy.DisneyAPIMickaelaTarazaga.mappers;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;
import java.util.List;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MapStructMapper  {
  
    CharacterSlimDto characterToCharacterSlimDto(CharacterEntity character);

    CharacterDto characterToCharacterDto(CharacterEntity character);

    CharacterEntity characterDtoToCharacter(CharacterDto character);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
                 nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    CharacterEntity updateCharacterFromDto(CharacterDto characterDto, @MappingTarget CharacterEntity character);

    List<CharacterSlimDto> charactersToCharacterSlimDtos(List<CharacterEntity> characters);

    List<CharacterDto> charactersToCharacterDtos(List<CharacterEntity> characters);

    MovieSlimDto movieToMovieSlimDto(MovieSeriesEntity movie);

    MovieDto movieToMovieDto(MovieSeriesEntity movie);

    MovieSeriesEntity movieDtoToMovie(MovieDto movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    MovieSeriesEntity updateMovieFromDto(MovieDto movieDto, @MappingTarget MovieSeriesEntity movie);

    List<MovieSlimDto> moviesToMovieSlimDtos(List<MovieSeriesEntity> movies);

    List<MovieDto> moviesToMovieDtos(List<MovieSeriesEntity> movies);

    List<GenreSlimDto> genresToGenreSlimDtos(List<GenreEntity> genres);


}
