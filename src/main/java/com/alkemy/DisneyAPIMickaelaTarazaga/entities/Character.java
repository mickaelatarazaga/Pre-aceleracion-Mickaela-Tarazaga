
package com.alkemy.DisneyAPIMickaelaTarazaga.entities;

import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@SQLDelete(sql= "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause="deleted=false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "characters")
public class Character {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String image; 
    
    private String name;
    
    private Integer age;
    
    private Float weight;
    
    private String history;
    
    private boolean deleted = Boolean.FALSE;
        
    //NOMBRE DE LA TABLA INTERMEDIA
    @JoinTable(name = "character_movie",
            //COMO SE JOINEA DE ESTE LADO
            joinColumns = @JoinColumn(name = "character_id"),
            //COMO SE JOINEA DE EL OTRO LADO
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @ManyToMany
    private Set<Movie> movies = new HashSet<>();

}
