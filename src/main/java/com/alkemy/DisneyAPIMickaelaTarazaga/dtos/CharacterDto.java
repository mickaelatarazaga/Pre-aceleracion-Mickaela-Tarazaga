package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import com.alkemy.DisneyAPIMickaelaTarazaga.entities.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Getter
@Setter
public class CharacterDto {

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotBlank(message = "ImageURL can't be blank")
    private String image;

    @NotNull
    private Integer age;

    @NotNull
    private Float weight;

    @NotBlank(message = "History can't be blank")
    private String history;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Movie> movies;

    
}
