package com.farjuce.appreservas.controller.dto;


import com.farjuce.appreservas.bd.appointment.Appointment;
import lombok.Data;

@Data
public class CustomerDTO {

    private String name;
    private String email;
    private Long phone_number;


}
