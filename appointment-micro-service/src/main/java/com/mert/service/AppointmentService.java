package com.mert.service;

import com.mert.dto.request.CreateRequestDto;
import com.mert.exception.AppointmentException;
import com.mert.exception.ErrorType;
import com.mert.repository.IAppointmentRepository;
import com.mert.repository.entity.Appointment;
import com.mert.utility.JwtTokenManager;
import com.mert.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService extends ServiceManager<Appointment,Long> {
    private final IAppointmentRepository repository;
    private final JwtTokenManager jwtTokenManager;
    public AppointmentService(IAppointmentRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public void createAppointmentDto(CreateRequestDto dto) {
        Optional<Long> userid = jwtTokenManager.getIdFromToken(dto.getToken());
        if(userid.isEmpty()){
            throw new AppointmentException(ErrorType.INVALID_TOKEN);
        }
        save(Appointment.builder()
                .userid(userid.get())
                .section(dto.getSection().toString())
                .day(dto.getDay())
                .hour(dto.getHour())
                .priority(dto.getPriority())
                .build());

    }
}
