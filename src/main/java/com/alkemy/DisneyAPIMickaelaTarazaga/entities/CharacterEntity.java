/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.DisneyAPIMickaelaTarazaga.entities;

import java.util.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author Mickaela Tarazaga
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "characters")

public class CharacterEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String image; 
    
    private String name;
    
    private Integer age;
    
    private Float weight;
    
    private String history;
    
    
    //NOMBRE DE LA TABLA INTERMEDIA
    @JoinTable(name = "character_movie",
            //COMO SE JOINEA DE ESTE LADO
            joinColumns = @JoinColumn(name = "character_id"),
            //COMO SE JOINEA DE EL OTRO LADO
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @ManyToMany
    private Set<MovieSeriesEntity> movies = new HashSet<>();


}
