package com.recipe.RecipeAPI.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipe.RecipeAPI.dtos.Ingredient;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @Lob
    String description;
    String imageUrl;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Ingredient> ingredients = new HashSet<>();

    @ElementCollection
    Set<String> steps = new HashSet<>() ;
    @ElementCollection
    Set<Integer>timers= new HashSet<>() ;

}
