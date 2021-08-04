package com.web.curation.error;

import org.omg.IOP.ExceptionDetailMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {NotFoundDataException.class})
    public ResponseEntity<Object> handleNotFoundDataException(NotFoundDataException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                "ExceptionDetailMessage.DATA_NOT_FOUND_MESSAGE",
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException,httpStatus);
    }
}
