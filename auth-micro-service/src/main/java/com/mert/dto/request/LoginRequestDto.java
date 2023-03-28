package com.mert.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotNull
    @Size(min = 3)
    String username;
    @NotNull
    @Size(min = 5)
    String password;
}
