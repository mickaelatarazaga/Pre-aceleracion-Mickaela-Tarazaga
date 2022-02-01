package com.alkemy.DisneyAPIMickaelaTarazaga.mappers;
import com.alkemy.DisneyAPIMickaelaTarazaga.dtos.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.*;
import com.alkemy.DisneyAPIMickaelaTarazaga.entities.Character;

import java.util.List;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CharacterMapper  {

    CharacterSlimDto characterToCharacterSlimDto(Character character);

    CharacterDto characterToCharacterDto(Character character);

    Character characterDtoToCharacter(CharacterDto character);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
                 nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Character updateCharacterFromDto(CharacterDto characterDto, @MappingTarget Character character);

    List<CharacterSlimDto> charactersToCharacterSlimDtos(List<Character> characters);

    List<CharacterDto> charactersToCharacterDtos(List<Character> characters);

    
}
