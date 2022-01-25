package com.alkemy.DisneyAPIMickaelaTarazaga.entities;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@SQLDelete(sql= "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause="deleted=false")
@Getter
@Setter
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
    
    private boolean deleted = Boolean.FALSE;
    
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

 
}
