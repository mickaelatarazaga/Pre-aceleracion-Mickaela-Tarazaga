package com.alkemy.DisneyAPIMickaelaTarazaga.entities;
import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String image; 
    
    private String name;
    
    @ManyToMany(mappedBy = "genres")
    private Set<MovieSeriesEntity> movies = new HashSet<>();
      
    
}
