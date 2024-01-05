package com.example.table_reservation_service.global.exception;

import com.example.table_reservation_service.global.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.table_reservation_service.global.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.table_reservation_service.global.type.ErrorCode.INVALID_REQUEST;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ErrorResponse customExceptionHandler(GlobalException e) {
        log.error("{} is occurred.", e.getErrorCode());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMassage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse dataIntegrityViolationExceptionHandler(
            DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException is occurred.", e);
        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorResponse usernameNotFoundExceptionHandler(UsernameNotFoundException e) {
        log.error("UsernameNotFoundException is occurred", e);
        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandler(Exception e) {
        log.error("Exception is occurred", e);
        return new ErrorResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getDescription());
    }
}
