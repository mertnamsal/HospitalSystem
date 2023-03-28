package com.mert.controller;

import com.mert.dto.request.CreateRequestDto;
import com.mert.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.mert.constants.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(APPOINTMENT)
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createAppointment(@RequestBody CreateRequestDto dto){
        appointmentService.createAppointmentDto(dto);
        return ResponseEntity.ok(true);
    }

}
