package com.farjuce.appreservas.controller.dto;

import lombok.Data;

@Data
public class AppointmentDTO {

    private Long id;
    private String date;
    private String start_time;
    private String end_time;
    private String state;
    private Long customer_id;
    private Long employee_id;
    private Long task_id;
}
