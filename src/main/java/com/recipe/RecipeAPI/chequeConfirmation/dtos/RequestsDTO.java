package com.recipe.RecipeAPI.chequeConfirmation.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RequestsDTO {
    private List<ChequeConfirmationResponseDTO> chequeRequests = new ArrayList<>();
}
