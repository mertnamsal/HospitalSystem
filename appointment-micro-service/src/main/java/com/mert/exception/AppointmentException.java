package com.mert.exception;

import lombok.Getter;

@Getter
public class AppointmentException extends RuntimeException {

    /**
     * Runtime dan miras aldığımız için hata mesajının kendisine iletilmesi gereklidir.
     */
    private final ErrorType errorType;
    public AppointmentException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;

    }
    public AppointmentException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
