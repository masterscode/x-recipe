package com.recipe.RecipeAPI.chequeConfirmation.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

	private String status;
	private String message;
	private T data;
}
