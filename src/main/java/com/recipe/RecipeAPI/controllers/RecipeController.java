package com.recipe.RecipeAPI.controllers;


import com.recipe.RecipeAPI.dtos.APIResponse;
import com.recipe.RecipeAPI.dtos.RecipeOverviewDto;
import com.recipe.RecipeAPI.models.Recipe;
import com.recipe.RecipeAPI.services.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeController {

    RecipeService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public APIResponse<List<RecipeOverviewDto>> getAllRecipes(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "page-size", required = false, defaultValue = "10") int pageSize
    ) {
        return
                service.getAllRecipe(PageRequest.of(page, pageSize));
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<APIResponse<Recipe>> getRecipe(@PathVariable Long recipeId) {
        var response = service.getRecipe(recipeId);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<APIResponse<?>> deleteRecipe(@PathVariable Long recipeId) {
        var response = service.deleteRecipe(recipeId);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
