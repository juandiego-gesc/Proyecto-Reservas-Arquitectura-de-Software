package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.logica.AppointmentLogic;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    private AppointmentLogic logic;

    public AppointmentController(AppointmentLogic logicAppointment) {
        this.logic = logicAppointment;
    }

    @GetMapping(path = "/appointments/getAll")
    public List<Appointment> getAllAppointments() {
        return logic.getAllAppointments();
    }

    @PostMapping(path = "/appointment/create")
    public void createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        logic.createAppointment(appointmentDTO);
    }

    @PostMapping(path = "/appointment/update")
    public void updateAppointment(AppointmentDTO appointmentDTO) {

    }

    @DeleteMapping(path = "/appointment/delete")
    public void deleteAppointment (AppointmentDTO appointmentDTO){

    }

}
