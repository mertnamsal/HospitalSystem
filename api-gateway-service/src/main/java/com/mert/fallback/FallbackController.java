package com.mert.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FallbackController {

    @GetMapping("/fallbackauth")
    public ResponseEntity<String> fallbackauth(){
        return ResponseEntity.ok("Knight online auth offline");
    }
    @GetMapping("/fallbackuser")
    public ResponseEntity<String> fallbackuser(){
        return ResponseEntity.ok("Knight online user offline");
    }
    @GetMapping("/fallbackappointment")
    public ResponseEntity<String> fallbackappointment(){
        return ResponseEntity.ok("Knight online appointment offline");
    }
}
