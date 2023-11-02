package com.farjuce.appreservas.controller.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AppointmentDTO {

    private Long id;

    @NotNull
    private String date;
    @NotNull
    private String startTime;
    @NotNull
    private String endTime;
    @NotNull
    private String state;
    @NotNull
    private Long customerId;
    @NotNull
    private Long employeeId;
    @NotNull
    private Long taskId;
}
