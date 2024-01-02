package com.challenge.pricesservice.infrastructure.exception;

import com.challenge.pricesservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PricesExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.message(ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
