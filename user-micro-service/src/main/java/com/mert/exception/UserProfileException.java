package com.mert.exception;

import lombok.Getter;

@Getter
public class UserProfileException extends RuntimeException {

    /**
     * Runtime dan miras aldığımız için hata mesajının kendisine iletilmesi gereklidir.
     */
    private final ErrorType errorType;
    public UserProfileException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;

    }
    public UserProfileException(ErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
