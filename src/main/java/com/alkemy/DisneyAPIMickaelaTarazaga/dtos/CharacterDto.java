package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import com.alkemy.DisneyAPIMickaelaTarazaga.entities.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    
}
