package com.farjuce.appreservas.controller.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AppointmentDTO {

    private Long id;


    @NonNull
    private String date;
    @NonNull
    private String start_time;
    @NonNull
    private String end_time;
    @NonNull
    private String state;
    @NonNull
    private Long customer_id;
    @NonNull
    private Long employee_id;
    @NonNull
    private Long task_id;
}
