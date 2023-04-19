package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfirmRequestDTO {

    private UpdateConfirmationRequestDTO request;
    private String chequeNumber;
    private String accountNumber;
}
