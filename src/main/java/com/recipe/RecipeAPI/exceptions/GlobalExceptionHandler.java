package com.recipe.RecipeAPI.exceptions;

import com.recipe.RecipeAPI.chequeConfirmation.dtos.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		ex.printStackTrace();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
		String error;
		for (FieldError fieldError : fieldErrors) {
			error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
			errors.add(error);
		}
		for (ObjectError objectError : globalErrors) {
			error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
			errors.add(error);
		}

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BaseResponse<?>> handleBadRequestExceptions(BadRequestException ex) {
		ex.printStackTrace();
		return ResponseEntity.badRequest().body(
			new BaseResponse<>("failed", ex.getMessage(), null)
		);
	}


	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<BaseResponse<?>> handleException(final Exception e) {
		e.printStackTrace();
		return ResponseEntity
			.internalServerError()
			.body(new BaseResponse<>("failed", e.getMessage(), null));

	}

}
