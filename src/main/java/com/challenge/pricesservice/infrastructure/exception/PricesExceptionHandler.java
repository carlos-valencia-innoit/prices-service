package com.challenge.pricesservice.infrastructure.exception;

import com.challenge.pricesservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PricesExceptionHandler {
    @ExceptionHandler({PriceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handlePriceNotFoundException(PriceNotFoundException ex) {
        return createResponseEntity(HttpStatus.NO_CONTENT, ex);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(HttpStatus httpStatus, Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code(httpStatus.value());
        errorResponse.message(ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
