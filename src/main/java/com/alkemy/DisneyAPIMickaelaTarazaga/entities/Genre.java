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
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Mickaela Tarazaga
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String image; 
    
    private String name;
    
    @ManyToMany(mappedBy = "genres")
    private Set<MovieSeries> movies = new HashSet<>();
      
    
}
