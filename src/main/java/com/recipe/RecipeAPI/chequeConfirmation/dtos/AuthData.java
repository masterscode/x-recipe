package com.recipe.RecipeAPI.chequeConfirmation.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthData {

    @JsonProperty("token")
    private String token;
}
