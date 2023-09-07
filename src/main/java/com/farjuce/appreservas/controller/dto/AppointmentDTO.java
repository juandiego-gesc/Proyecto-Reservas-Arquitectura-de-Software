package com.farjuce.appreservas.controller.dto;

import lombok.Data;

@Data
public class AppointmentDTO {

    Long appointmentId;
    String Date;
    private String startTime;
    private String endTime;
    private String status;

}
