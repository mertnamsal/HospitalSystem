package com.mert.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @Size(min = 3,max = 20)
    @NotBlank
    String username;
    @NotBlank
    @Size(min = 5,max = 64)
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
//            message = "Şifre enaz 8 Karakter, Enaz bir büyük bir küçük harf, Sayı ve özel karakterden oluşmalıdır.")
    String password;
    @NotBlank
    @Size(min = 5,max = 64)
    String repassword;
    @Email
    String email;
    LocalDate date;
}
