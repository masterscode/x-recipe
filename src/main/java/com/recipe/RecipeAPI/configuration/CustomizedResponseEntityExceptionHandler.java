package com.recipe.RecipeAPI.configuration;

import com.recipe.RecipeAPI.dtos.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIResponse<Object> apiResponse = new APIResponse<>();
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Validation Failed");
        apiResponse.setData(ex.getBindingResult().getFieldErrors());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {


        log.error(e.getMessage(), e);

        APIResponse<Object> apiResponse = new APIResponse<>();
        apiResponse.setSuccess(false);
        apiResponse.setMessage("An unknown error has occured");
        apiResponse.setData(e.getMessage());
        apiResponse.setMeta(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleNoSuchElementException(EntityNotFoundException e) {


        log.error(e.getMessage(), e);

        APIResponse<Object> apiResponse = new APIResponse<>();
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Resource retrieval exception");
        apiResponse.setData(e.getMessage());
        apiResponse.setMeta(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
