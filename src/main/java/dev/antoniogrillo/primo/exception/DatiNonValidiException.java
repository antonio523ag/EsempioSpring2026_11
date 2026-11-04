package dev.antoniogrillo.primo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatiNonValidiException extends RuntimeException{

    private String campo;

    public DatiNonValidiException(String campo,String message){
        super(message);
        this.campo=campo;
    }
}
