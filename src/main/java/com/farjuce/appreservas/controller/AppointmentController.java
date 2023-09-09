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
    public boolean createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        logic.createAppointment(appointmentDTO);
        return true;
    }

    @PostMapping(path = "/appointment/update")
    public void updateAppointment(AppointmentDTO appointmentDTO) {

    }

    @DeleteMapping(path = "/appointment/cancel")
    public void deleteAppointment (AppointmentDTO appointmentDTO){

    }
    @PutMapping("/{task_id}/appointment/{appointment_id}")
    public Appointment TaskAppointment(
            @PathVariable Long task_id,
            @PathVariable Long appointment_id)
    {
        return logic.relationCustomer(task_id, appointment_id);

    }

    @PutMapping("/{customer_id}/appointment/{appointment_id}")
    public Appointment customerAppointment(
            @PathVariable Long customer_id,
            @PathVariable Long appointment_id)
    {
        return logic.relationCustomer(customer_id, appointment_id);

    }
    @PutMapping("/{employee_id}/appointment/{appointment_id}")
    public Appointment employeeAppointment(
            @PathVariable Long employee_id,
            @PathVariable Long appointment_id)
    {
        return logic.relationCustomer(employee_id, appointment_id);

    }

}
