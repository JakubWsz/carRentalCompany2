package pl.kuba.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleRuntimeException(RuntimeException e){
        e.printStackTrace();
        Map<String,String> body = new HashMap<>();
        body.put("timestamp", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format(LocalDateTime.now()));
        body.put("message", e.getMessage());
        body.put("code", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }
}
