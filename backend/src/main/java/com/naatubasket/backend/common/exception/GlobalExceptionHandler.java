package com.naatubasket.backend.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.naatubasket.backend.common.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorResponse.builder()
                        .success(false)
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("Not Found")
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicate(
            DuplicateResourceException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiErrorResponse.builder()
                        .success(false)
                        .status(HttpStatus.CONFLICT.value())
                        .error("Conflict")
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(ApiErrorResponse.builder()
                        .success(false)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Validation Failed")
                        .message(message)
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraint(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        return ResponseEntity.badRequest()
                .body(ApiErrorResponse.builder()
                        .success(false)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Validation Failed")
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(
            Exception ex,
            HttpServletRequest request) {

        return ResponseEntity.internalServerError()
                .body(ApiErrorResponse.builder()
                        .success(false)
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error("Internal Server Error")
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }
}