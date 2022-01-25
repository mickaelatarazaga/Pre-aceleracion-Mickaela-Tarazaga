package com.alkemy.DisneyAPIMickaelaTarazaga.entities;
import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@SQLDelete(sql= "UPDATE genre SET deleted = true WHERE id=?")
@Where(clause="deleted=false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String image; 
    
    private String name;
    
    private boolean deleted = Boolean.FALSE;
    
    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();


}
