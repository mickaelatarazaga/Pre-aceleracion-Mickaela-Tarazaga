package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class CharacterDto {

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private String id;

    private String name;

    private String image;

    private Integer age;

    private Float weight;

    private String history;

}
