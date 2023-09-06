package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.logica.AppointmentLogic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

    private AppointmentLogic logic;

    public AppointmentController(AppointmentLogic logic) {
        this.logic = logic;
    }

    // Todo: Implement this class

    @PostMapping("/appointment/create")
    public void createAppointment(AppointmentDTO appointmentDTO) {
//        logic.create();
    }

    @PostMapping("/appointment/update")
    public void updateAppointment(AppointmentDTO appointmentDTO) {
//        logic.update();
    }

}
