package com.game.SoloFighter.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.game.SoloFighter.model.BackendReponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BackendReponse> handleRuntimeException(RuntimeException e){
        return ResponseEntity.badRequest().body(new BackendReponse(false,e.getMessage()));        
    }

}
