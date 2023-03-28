package com.mert.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    /**
     *
     */
    BAD_REQUEST_ERROR(1201,"Geçersiz Parametre Girişi Yaptınız",BAD_REQUEST),
    AUTH_PASSWORD_ERROR(1301,"Şifreler uyuşmuyor",BAD_REQUEST),
    AUTH_USERNAME_ERROR(1302,"Kullanıcı adı zaten kayıtlıdır.",BAD_REQUEST),
    AUTH_LOGIN_ERROR(1303,"Kullanıcı adı yada şifre hatalıdır.",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Sunucuda beklenmeyen hata",INTERNAL_SERVER_ERROR),
    TOKEN_ERROR(3001,"Token oluşturma hatası",INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(3002,"Geçersiz token",BAD_REQUEST),
    KULLANICI_BULUNAMADI(2301,"Aradağınız id ye ait kullanıcıya bulunamamıştır.",INTERNAL_SERVER_ERROR);
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
