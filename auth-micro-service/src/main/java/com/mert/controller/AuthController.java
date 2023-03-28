package com.mert.controller;

import com.mert.dto.request.LoginRequestDto;
import com.mert.dto.request.RegisterRequestDto;
import com.mert.dto.response.LoginResponseDto;
import com.mert.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.mert.constants.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+VERSION+AUTH)
public class AuthController {

    private final AuthService authService;
    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody RegisterRequestDto dto){
        authService.register(dto);
        return ResponseEntity.ok(true);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponseDto> doLogin(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));

    }
}
