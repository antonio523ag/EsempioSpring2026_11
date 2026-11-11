package dev.antoniogrillo.primo.exception;

import dev.antoniogrillo.primo.dto.MessageDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GestoreException {

    @ExceptionHandler(DatiNonValidiException.class)
    public ResponseEntity<MessageDTO> datiNonValidiException(DatiNonValidiException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(ex.getCampo(),ex.getMessage(),null));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageDTO> httpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest w){
        String path=w.getDescription(true);
        MessageDTO m=new MessageDTO("intero body",ex.getMessage(),path);
        return ResponseEntity.badRequest().body(m);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> result=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String errorMessage=error.getDefaultMessage();
            result.put(fieldName,errorMessage);
        });
        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> constraintViolationException(ConstraintViolationException ex){
        Map<String,String> result=new HashMap<>();
        ex.getConstraintViolations().forEach((error)->{
            String fieldName=error.getPropertyPath().toString();
            String[] partial =fieldName.split("\\.");
            String errorMessage=error.getMessage();
            result.put(partial[partial.length-1],errorMessage);
        });
        return ResponseEntity.badRequest().body(result);
    }
}
