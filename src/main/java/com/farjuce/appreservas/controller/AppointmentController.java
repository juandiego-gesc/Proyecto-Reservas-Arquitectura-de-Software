package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.logica.AppointmentLogic;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

    private AppointmentLogic logicAppointment;

    public AppointmentController(AppointmentLogic logicAppointment) {
        this.logicAppointment = logicAppointment;
    }


    @PostMapping(path = "/appointment/create")
    public void createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        logicAppointment.createAppointment(appointmentDTO);
    }

    @PostMapping(path = "/appointment/update")
    public void updateAppointment(AppointmentDTO appointmentDTO) {

    }

    @DeleteMapping(path = "/appointment/delete")
    public void deleteAppointment (AppointmentDTO appointmentDTO){

    }

}
