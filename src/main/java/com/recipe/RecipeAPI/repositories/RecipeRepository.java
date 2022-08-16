package com.recipe.RecipeAPI.repositories;

import com.recipe.RecipeAPI.dtos.RecipeOverviewDto;
import com.recipe.RecipeAPI.models.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RecipeRepository  extends JpaRepository<Recipe, Long> {


    @Query("select  new com.recipe.RecipeAPI.dtos.RecipeOverviewDto(r.id, r.name,r.description, r.imageUrl) from Recipe r")
    Page<RecipeOverviewDto> getRecipeOverview(Pageable pageable);

    @Query("select r from Recipe r  join fetch r.ingredients  join fetch r.steps  join fetch r.timers where r.id =:id")
    Optional<Recipe> findById(Long id);

}
