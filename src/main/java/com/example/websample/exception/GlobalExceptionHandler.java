package com.example.websample.exception;

import com.example.websample.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponse> handleIllegalAccessException(
            IllegalAccessException e){
        log.error("IllgalAccessException is occured",e);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR,
                        "IllegalAccessException is occurred"));
    }

    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handleWebSampleException(
            WebSampleException e){
        log.error("WebSampleException is occured",e);
        return ResponseEntity
                .status(HttpStatus.INSUFFICIENT_STORAGE) // 507
                .body(new ErrorResponse(e.getErrorCode(),
                        "WebSampleException is occurred"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception e){
        log.error("Exception is occured",e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 507
                .body(new ErrorResponse(ErrorCode.INTERTER_SERVER_ERROR,
                        "Exception is occurred"));
    }
}
