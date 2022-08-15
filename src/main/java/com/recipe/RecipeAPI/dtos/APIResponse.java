package com.recipe.RecipeAPI.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class APIResponse <T>{

    boolean success;
    LocalDateTime time = LocalDateTime.now();
    CharSequence message;
    HttpStatus status;

    Object meta;
    T data;


}
