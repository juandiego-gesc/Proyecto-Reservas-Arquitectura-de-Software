package com.farjuce.appreservas.controller.dto;

import lombok.Data;

@Data
public class AppointmentDTO {

    long appointmentId;
    String appointmentDate;
    int appointmentStartTime;
    int appointmentEndTime;
    String appointmentStatus;
    long ServiceId;

}
