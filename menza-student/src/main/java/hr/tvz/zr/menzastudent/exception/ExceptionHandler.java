package hr.tvz.zr.menzastudent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ClientException.class)
    // @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ClientErrorResponse> handleAnyException(Exception exc){
        //create  Error response
        ClientErrorResponse error = new ClientErrorResponse ();
        error.setStatus("FAILED");
        error.setMessage(exc.getMessage());
        error.setTimestamps(System.currentTimeMillis());
        //return entity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

