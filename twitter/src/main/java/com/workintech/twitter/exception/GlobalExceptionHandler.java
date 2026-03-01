package com.workintech.twitter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserErrorResponse> handleUserException(UserException userException) {
        UserErrorResponse userErrorResponse = new UserErrorResponse();
        userErrorResponse.setStatus(userException.getHttpStatus().value());
        userErrorResponse.setMessage(userException.getMessage());
        userErrorResponse.setTimestamp(System.currentTimeMillis());
        userErrorResponse.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(userException.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<UserErrorResponse> handleUserException(MethodArgumentTypeMismatchException exception) {
        UserErrorResponse userErrorResponse = new UserErrorResponse();
        userErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        userErrorResponse.setMessage("MethodArgumentTypeMismatchException");
        userErrorResponse.setTimestamp(System.currentTimeMillis());
        userErrorResponse.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserErrorResponse> handleUserException(Exception exception)
    {
        UserErrorResponse userErrorResponse = new UserErrorResponse();
        userErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        userErrorResponse.setMessage("Exception");
        userErrorResponse.setTimestamp(System.currentTimeMillis());
        userErrorResponse.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
