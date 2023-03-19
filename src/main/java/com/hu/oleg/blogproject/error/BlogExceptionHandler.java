package com.hu.oleg.blogproject.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.LocalDateTime;

@ControllerAdvice
public class BlogExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFound exc, WebRequest request){
//        var map = new HashMap<String, String>();
//
//        return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);

        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,exc.getMessage());
        problemDetails.setType(URI.create("http://localhost:8080/errors/not-found.html"));
        problemDetails.setProperty("timestamp", LocalDateTime.now());
        problemDetails.setProperty("field-name", exc.getFieldName());
        problemDetails.setProperty("field-value", exc.getFieldValue());

        return problemDetails;
    }
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception exc, WebRequest request){
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,exc.getMessage());
        problemDetails.setProperty("timestamp", LocalDateTime.now());

        return problemDetails;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
//exc->bindingResult->errors(List<ValidationFieldError>)-> (field,defaultMessage)
    public ProblemDetail handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exc, WebRequest request){
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        problemDetails.setProperty("timestamp", LocalDateTime.now()); //no trace, please.


        //map: problemDetails.setProperty();
        for (FieldError fieldError : exc.getFieldErrors()) {
            problemDetails.setProperty("Field name:" + fieldError.getField(), fieldError.getDefaultMessage());
            problemDetails.setProperty("rejected value for " + fieldError.getField(), fieldError.getRejectedValue());
        }

        return problemDetails;
    }

}
