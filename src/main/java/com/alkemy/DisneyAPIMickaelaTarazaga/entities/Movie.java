package com.alkemy.DisneyAPIMickaelaTarazaga.entities;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String image; 
    
    private String title;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
        
    private Integer score;
    
    @ManyToMany(mappedBy = "movies")
    private Set<Character> characters = new HashSet<>();

    
    //NOMBRE QUE SE LE DARÁ A LA TABLA INTERMEDIA
    @JoinTable(name = "movie_genre",
            //COMO SE JOINEA DE ESTE LADO
            joinColumns = @JoinColumn(name = "movie_id"),
            //COMO SE JOINEA DEL OTRO LADO
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany
    private Set<Genre> genres = new HashSet<>();

 
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
    
    
    
}
