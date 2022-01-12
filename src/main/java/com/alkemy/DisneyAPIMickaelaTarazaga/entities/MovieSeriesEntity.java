package com.alkemy.DisneyAPIMickaelaTarazaga.entities;
import java.awt.Image;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "movies_series")
public class MovieSeriesEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String image; 
    
    private String title;
    
    @Column(name = "creationDate")
    @DateTimeFormat(pattern = "DD/MM/YYYY" )
    private Date creationDate;
    
    
    private Integer score;
    
    
    @ManyToMany(mappedBy = "movies")
    private Set<CharacterEntity> characters = new HashSet<>();

    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany
    private Set<GenreEntity> genres = new HashSet<>();
    
    
}
