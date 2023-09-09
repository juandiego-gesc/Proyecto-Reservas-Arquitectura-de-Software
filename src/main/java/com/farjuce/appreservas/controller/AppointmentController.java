package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
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
    public boolean createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        logic.createAppointment(appointmentDTO);
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
        return logic.relationCustomer(customer_id, appointment_id);
    }

    @GetMapping(path = "/getMyAppointment/{id}")
    public List<Appointment> getMyAppointment(@PathVariable Long id){
        return logic.getMyAppointments(id);
    }
}
