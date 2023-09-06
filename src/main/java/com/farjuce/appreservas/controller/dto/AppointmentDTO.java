package com.farjuce.appreservas.controller.dto;

import lombok.Data;

@Data
public class AppointmentDTO {

    int appointmentId;
    String appointmentDate;
    int appointmentStartTime;
    int appointmentEndTime;
    String appointmentStatus;
    int ServiceId;


}
