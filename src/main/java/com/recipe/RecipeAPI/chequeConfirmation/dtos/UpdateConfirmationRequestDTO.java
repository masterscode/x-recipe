package com.recipe.RecipeAPI.chequeConfirmation.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateConfirmationRequestDTO {
	private String branchCode;
	private Boolean cleared;
	private Boolean status;
}
