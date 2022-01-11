/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.DisneyAPIMickaelaTarazaga.entities;

import java.awt.Image;
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

public class Character {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String image; 
    
    private String name;
    
    private Integer age;
    
    private Float weight;
    
    private String history;
    
    @JoinTable(name = "character_movie",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @ManyToMany
    private Set<MovieSeries> movies = new HashSet<>();


}
