package dev.antoniogrillo.primo.exception;

import lombok.Getter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;

@Getter
public class GraphQLException extends RuntimeException{
    private final ErrorType status;

    public GraphQLException(ErrorType status, String message){
        super(message);
        this.status = status;
    }
}
