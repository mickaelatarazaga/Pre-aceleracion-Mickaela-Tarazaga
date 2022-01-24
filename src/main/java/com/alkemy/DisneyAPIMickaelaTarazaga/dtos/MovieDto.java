package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


public class MovieDto {

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Title can't be blank")
    private String title;

    @NotBlank(message = "Image can't be blank")
    private String image;

    @NotNull(message = "Date can't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime creationDate;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<CharacterSlimDto> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterSlimDto> characters) {
        this.characters = characters;
    }

    public List<GenreSlimDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreSlimDto> genres) {
        this.genres = genres;
    }

    
}
