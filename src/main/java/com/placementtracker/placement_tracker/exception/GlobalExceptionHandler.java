package com.placementtracker.placement_tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(AlreadyAppliedException.class)
    public ResponseEntity<String> handleAlreadyAppliedException(AlreadyAppliedException e) {
        return ResponseEntity
                .status ( HttpStatus.CONFLICT )
                .body ( e.getMessage () );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundEception(ResourceNotFoundException e) {
        return ResponseEntity
                .status ( HttpStatus.NOT_FOUND )
                .body ( e.getMessage () );
    }
}
