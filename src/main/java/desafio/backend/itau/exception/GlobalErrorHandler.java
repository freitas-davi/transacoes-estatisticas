package desafio.backend.itau.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> argumentNotValid(){
        return ResponseEntity.unprocessableContent().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> messageNotReadble(){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Void> invalidTransaction(){
        return ResponseEntity.unprocessableContent().build();
    }

}
