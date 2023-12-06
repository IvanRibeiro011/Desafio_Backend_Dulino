package com.dulino.desafio.handler;

import com.dulino.desafio.exceptions.*;
import com.dulino.desafio.handler.messages.ApiErrorMessage;
import com.dulino.desafio.handler.messages.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ApiErrorMessage> userNotFoundException(UserNotFoundException e, HttpServletRequest request){
        return resourcesNotFoundMessageCreation(request, e.getMessage(), e);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<ApiErrorMessage> userAlreadyExists(UserAlreadyExistsException e, HttpServletRequest request){
        return resourceAlreadyExistsMessageCreation(request, e.getMessage(), e);
    }
    @ExceptionHandler(VehicleNotFoundException.class)
    ResponseEntity<ApiErrorMessage> vehicleNotFound(VehicleNotFoundException e, HttpServletRequest request){
        return resourcesNotFoundMessageCreation(request, e.getMessage(), e);
    }

    @ExceptionHandler(VehiclePlateAlreadyRegisteredException.class)
    ResponseEntity<ApiErrorMessage> vehiclePlateAlreadyRegistered(VehiclePlateAlreadyRegisteredException e, HttpServletRequest request){
        return resourceAlreadyExistsMessageCreation(request, e.getMessage(), e);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    ResponseEntity<ApiErrorMessage> taskNotFound(TaskNotFoundException e,HttpServletRequest request){
        return resourcesNotFoundMessageCreation(request, e.getMessage(), e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorMessage> validationError(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Validation exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<ApiErrorMessage> usernameNotFound(UsernameNotFoundException e,HttpServletRequest request){
        return resourcesNotFoundMessageCreation(request, e.getMessage(), e);
    }

    @ExceptionHandler(NonExistentRoleException.class)
    ResponseEntity<ApiErrorMessage> nonExistingRole(NonExistentRoleException e, HttpServletRequest request){
        return resourcesNotFoundMessageCreation(request, e.getMessage(), e);
    }

    private <T> ResponseEntity<ApiErrorMessage> resourceAlreadyExistsMessageCreation(HttpServletRequest request, String message, T e) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        ApiErrorMessage err = new ApiErrorMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Resource already exists");
        err.setMessage(message);
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    private <T> ResponseEntity<ApiErrorMessage> resourcesNotFoundMessageCreation(HttpServletRequest request, String message, T e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorMessage err = new ApiErrorMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Resource not found");
        err.setMessage(message);
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
