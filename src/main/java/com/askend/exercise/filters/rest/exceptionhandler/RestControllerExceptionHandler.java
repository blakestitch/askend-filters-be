package com.askend.exercise.filters.rest.exceptionhandler;

import com.askend.exercise.filters.domain.validator.CriteriaValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandler {

    @ExceptionHandler(CriteriaValidationException.class)
    public ResponseEntity<String> handleCriteriaValidationException(CriteriaValidationException e) {
        log.error("Error happened when validating criteria", e);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
