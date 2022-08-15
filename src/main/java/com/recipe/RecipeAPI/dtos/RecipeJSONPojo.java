package com.recipe.RecipeAPI.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeJSONPojo {



    String name;
    String description;
    String imageUrl;

    List<Ingredient> ingredients;
    List<String>steps;
    List<Integer>timers;


}
