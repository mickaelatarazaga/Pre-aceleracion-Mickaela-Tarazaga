package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Title can't be blank")
    private String title;

    @NotBlank(message = "Image can't be blank")
    private String image;

    @NotNull(message = "Date can't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String creationDate;

    @NotNull(message = "Score can't be null")
    @Min(value = 0, message = "Score must be greater or equal to 0")
    @Max(value = 10, message = "Score must be less or equal to 10")
    private Integer score;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CharacterSlimDto> characters;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GenreSlimDto> genres;

    
}
