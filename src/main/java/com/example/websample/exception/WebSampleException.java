package com.example.websample.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WebSampleException extends RuntimeException{
    //RuntimeException을 상용받은 예외처리임
    private ErrorCode errorCode;
    private String message;
}
