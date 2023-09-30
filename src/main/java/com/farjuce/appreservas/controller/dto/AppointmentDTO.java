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
    private String start_time;
    @NotNull
    private String end_time;
    @NotNull
    private String state;
    @NotNull
    private Long customer_id;
    @NotNull
    private Long employee_id;
    @NotNull
    private Long task_id;
}
