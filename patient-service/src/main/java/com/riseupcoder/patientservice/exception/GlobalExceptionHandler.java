package com.riseupcoder.patientservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err ->
                fieldErrors.put(err.getField(), err.getDefaultMessage()));

        ProblemDetail pd = createProblemDetail("Method Argument Not Valid",
                "Please check the errors field for more details.", HttpStatus.BAD_REQUEST);

        pd.setProperty("errors", fieldErrors);

        return ResponseEntity.badRequest().body(pd);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ProblemDetail pd = createProblemDetail("Resource Not Found", ex.getMessage(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pd);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ProblemDetail> handleAPIException(APIException ex) {
        ProblemDetail pd = createProblemDetail("API Exception",
                ex.getMessage(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(pd);
    }


    // ------------------------------------- Utils -------------------------------------------

    public ProblemDetail createProblemDetail(String title, String detail, HttpStatus status) {

        ProblemDetail pd = ProblemDetail.forStatus(status);

        pd.setTitle(title);
        pd.setDetail(detail);
        pd.setProperty("timestamp", Instant.now());
        pd.setProperty("success", false);

        return pd;
    }
}
