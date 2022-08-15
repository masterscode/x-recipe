package com.recipe.RecipeAPI.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Ingredient {
    String quantity;
    String name;
    String type;

}
