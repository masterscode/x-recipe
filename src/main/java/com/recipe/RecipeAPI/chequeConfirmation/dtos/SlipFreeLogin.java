package com.recipe.RecipeAPI.chequeConfirmation.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlipFreeLogin {
	private Long clientID;
	private String password;
}
