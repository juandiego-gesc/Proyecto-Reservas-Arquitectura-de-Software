package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.logica.AppointmentLogic;

import org.springframework.web.bind.annotation.*;

@RestController
public class AppointmentController {

    private AppointmentLogic logicAppointment;

    public AppointmentController(AppointmentLogic logicAppointment) {
        this.logicAppointment = logicAppointment;
    }


    @PostMapping(path = "/appointment/create")
    public boolean createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        logicAppointment.createAppointment(appointmentDTO);
        return true;
    }

    @PostMapping(path = "/appointment/update")
    public void updateAppointment(AppointmentDTO appointmentDTO) {

    }

    @DeleteMapping(path = "/appointment/delete")
    public void deleteAppointment (AppointmentDTO appointmentDTO){

    }

    @PutMapping("/{customer_id}/appointment/{appointment_id}")
    public Appointment customerAppointment(
            @PathVariable Long customer_id,
            @PathVariable Long appointment_id)
    {
        return logicAppointment.relation(customer_id, appointment_id);

    }

}
