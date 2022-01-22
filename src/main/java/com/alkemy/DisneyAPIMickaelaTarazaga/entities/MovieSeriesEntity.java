package com.alkemy.DisneyAPIMickaelaTarazaga.entities;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movies")
public class MovieSeriesEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String image; 
    
    private String title;
    
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    
    private Integer score;
    
    
    @ManyToMany(mappedBy = "movies")
    private Set<CharacterEntity> characters = new HashSet<>();

    
    //NOMBRE QUE SE LE DARÁ A LA TABLA INTERMEDIA
    @JoinTable(name = "movie_genre",
            //COMO SE JOINEA DE ESTE LADO
            joinColumns = @JoinColumn(name = "movie_id"),
            //COMO SE JOINEA DEL OTRO LADO
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany
    private Set<GenreEntity> genres = new HashSet<>();
    
    
}
