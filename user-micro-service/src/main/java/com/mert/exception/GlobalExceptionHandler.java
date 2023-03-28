package com.mert.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

import static com.mert.exception.ErrorType.BAD_REQUEST_ERROR;


@ControllerAdvice
public class
GlobalExceptionHandler {
    /**
     * ExceptionHandler -> Kendisine v erilen sınıfın uygulama içinde istisna fırlatması durumunda devreye girer
     * ve o hatayı yakalar.
     * @param exception
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception){
        ErrorType errorType = ErrorType.INTERNAL_ERROR;
        return new ResponseEntity<>(createError(errorType,exception), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserProfileException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleSpringMonoException(UserProfileException exception){
        return new ResponseEntity<>(createError(exception.getErrorType(),exception),exception.getErrorType().getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createError(errorType, exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }



    /**
     * 1- Hatalar tek String şeklinde dönülmemelidir.
     * 2- Hataları bir JsonObject-Entity şeklinde dönmelisiniz.
     * 3- Dönüş nesnesini bir method ile oluşturmak mantıklıdır. çünkü method içinde
     * loglama yapabilir geribildirtimleri toplayabilirsiniz.
     */
    private ErrorMessage createError(ErrorType errorType, Exception exception){
        System.out.println("HATA OLDU.....: "+ exception.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }


}