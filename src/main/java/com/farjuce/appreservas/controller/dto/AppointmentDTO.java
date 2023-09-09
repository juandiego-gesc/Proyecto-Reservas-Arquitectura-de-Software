package com.farjuce.appreservas.controller.dto;

import com.farjuce.appreservas.bd.customer.Customer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDTO {

    private LocalDate date;
    private String start_time;
    private String end_time;
    private String state;
    private Long customer_id;
    private Long employee_id;
    private Long task_id;
}
