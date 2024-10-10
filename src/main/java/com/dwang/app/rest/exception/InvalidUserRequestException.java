package com.dwang.app.rest.exception;

public class InvalidUserRequestException extends RuntimeException{
    public InvalidUserRequestException(String message){
        super(message);
    }
}
