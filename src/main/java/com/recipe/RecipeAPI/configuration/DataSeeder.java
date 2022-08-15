package com.recipe.RecipeAPI.configuration;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.RecipeAPI.dtos.RecipeJSONPojo;
import com.recipe.RecipeAPI.models.Recipe;
import com.recipe.RecipeAPI.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Configuration
@Log
@RequiredArgsConstructor
public class DataSeeder {

    private final RecipeRepository recipeRepository;

    @EventListener
    public void seedDB(ContextRefreshedEvent event) throws ExecutionException, InterruptedException {

        CompletableFuture.supplyAsync(recipeRepository::count)
                .thenApplyAsync(count -> {

                    try{
                        return Files.readString(Paths.get("src/main/resources/seed-data/recipe.json"), Charset.defaultCharset());

                    } catch (Exception e) {
                        return null;
                    }
                }).thenApplyAsync(json -> {

                    if (Objects.isNull(json)) return new ArrayList<RecipeJSONPojo>();

                    log.log(Level.INFO, "Starting application data seeding ==> " + event.getTimestamp());

                    ObjectMapper objectMapper = new ObjectMapper();
                    try {

                        return objectMapper.readValue(json, new TypeReference<List<RecipeJSONPojo>>() {
                        });


                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return new ArrayList<RecipeJSONPojo>();
                    }

                })
                .thenAcceptAsync( (List<RecipeJSONPojo> pojo) ->{

                    List<Recipe> recipes = pojo.stream().map(p->
                        Recipe.builder()
                                .name(p.getName())
                                .description(p.getDescription())
                                .imageUrl(p.getImageUrl())
                                .build()
                    ).toList();

                   recipeRepository.saveAll(recipes);

                })
                .get();


    }


}
