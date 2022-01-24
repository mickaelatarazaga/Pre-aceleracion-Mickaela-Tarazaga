package com.alkemy.DisneyAPIMickaelaTarazaga.entities;
import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String image; 
    
    private String name;
    
    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    
    
}
