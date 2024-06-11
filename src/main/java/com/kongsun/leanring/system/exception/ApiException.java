package com.kongsun.leanring.system.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@RequiredArgsConstructor
public class ApiException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;

}