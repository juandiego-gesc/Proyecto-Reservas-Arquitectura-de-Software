package com.farjuce.appreservas.controller.dto;

import com.farjuce.appreservas.bd.customer.Customer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDTO {

    long appointment_id;
    private LocalDate date;
    String start_time;
    private String end_time;
    private String state;
    private Customer customer;

}
