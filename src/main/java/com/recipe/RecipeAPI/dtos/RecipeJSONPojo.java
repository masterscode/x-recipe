package com.recipe.RecipeAPI.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeJSONPojo {



    String name;
    String description;
    String imageURL;

    Set<Ingredient> ingredients;
    Set<String>steps;
    Set<Integer>timers;


}
