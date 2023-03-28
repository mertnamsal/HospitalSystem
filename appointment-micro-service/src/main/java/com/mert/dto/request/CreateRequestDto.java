package com.mert.dto.request;

import com.mert.repository.enums.ESection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestDto {
    String token;
    ESection section;
    LocalDate day;
    Integer hour;
    Integer priority;


}
