package com.mert.controller;

import com.mert.dto.request.UserSaveRequestDto;
import com.mert.dto.response.UserFindAllResponseDto;
import com.mert.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mert.constants.EndPoints.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(API+VERSION+USER)
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping(FINDALL)
    public ResponseEntity<List<UserFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(userProfileService.findAllDto());
    }
}
