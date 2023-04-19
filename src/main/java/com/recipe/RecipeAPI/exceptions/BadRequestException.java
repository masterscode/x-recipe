package com.recipe.RecipeAPI.exceptions;

public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 8589601677893112777L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
