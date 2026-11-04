package dev.antoniogrillo.primo.exception;

import dev.antoniogrillo.primo.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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
}
