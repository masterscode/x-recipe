package com.recipe.RecipeAPI.services;


import com.recipe.RecipeAPI.dtos.APIResponse;
import com.recipe.RecipeAPI.dtos.RecipeOverviewDto;
import com.recipe.RecipeAPI.models.Recipe;
import com.recipe.RecipeAPI.repositories.RecipeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log
public class RecipeService {
    RecipeRepository repository;

    public APIResponse<List<RecipeOverviewDto>> getAllRecipe(Pageable pagination) {
        return CompletableFuture.supplyAsync(() -> repository.getRecipeOverview(pagination))
                .thenApplyAsync(dtoPage ->
                        APIResponse.<List<RecipeOverviewDto>>builder()
                                .success(true)
                                .time(LocalDateTime.now())
                                .meta(Map.<String, Object>of(
                                        "totalElement", dtoPage.getTotalElements(),
                                        "page", dtoPage.getNumber(),
                                        "pageSize", dtoPage.getSize(),
                                        "totalPages", dtoPage.getTotalPages()
                                ))
                                .message("Recipe(s) successfully retrieved")
                                .data(dtoPage.getContent())
                                .build())
                .handleAsync((data, ex) -> {
                    if (Objects.nonNull(ex)) log.log(Level.SEVERE, ex::getMessage);
                    return data;
                }).join();

    }

    public APIResponse<Recipe> getRecipe(Long recipeId) {
        return CompletableFuture.supplyAsync(() -> repository.findById(recipeId).orElseThrow(()->new EntityNotFoundException("Entity ( Recipe ) not found")))
                .thenApplyAsync(recipe ->
                        APIResponse.<Recipe>builder()
                                .success(true)
                                .time(LocalDateTime.now())
                                .status(HttpStatus.OK)
                                .message("Recipe successfully retrieved")
                                .data(recipe)
                                .build())
                .exceptionallyAsync(ex -> {
                    log.log(Level.SEVERE, ex.getMessage(), ex);

                    return APIResponse.<Recipe>builder()
                                .message(ex.getCause().getMessage())
                                .success(false)
                                .status(HttpStatus.BAD_REQUEST)
                                .success(false).build();

                })
                .thenApplyAsync(data -> data).join();
    }


    public APIResponse<?> deleteRecipe(Long recipeId) {

        Recipe found = repository.findById(recipeId).orElseThrow();
        repository.delete(found);
        return  APIResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Recipe successfully deleted")
                .time(LocalDateTime.now())
                .build();
    }

}
