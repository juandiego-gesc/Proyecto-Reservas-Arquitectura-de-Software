package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.employee.Employee;
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

    @PostMapping(path = "/appointment/create")
    public String createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            return "Appointment created with id: " + logic.createAppointment(appointmentDTO).getAppointment_id();
        } catch (Exception e) {
            return "Appointment not created: " + e.getMessage();
        }
    }

    @GetMapping(path = "/getMyAppointment/{id}")
    public List<Appointment> getMyAppointment(@PathVariable Long id) {
        return logic.getMyAppointments(id);
    }

    @GetMapping(path = "/appointments/getAll")
    public List<Appointment> getAllAppointments() {
        return logic.getAllAppointments();
    }

    @GetMapping(path = "/appointments/getAvailable")
    public List<Employee> getAvailableAppointments(@RequestParam Long task_id, @RequestParam String start_time,
            @RequestParam String end_time, @RequestParam String date) {
        return logic.getAvailabilityByTimeAndTask(task_id, start_time, end_time, date);
    }

    @PutMapping(path = "/appointment/update")
    public void updateAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        logic.updateAppointment(appointmentDTO);

    }

    @DeleteMapping(path = "/appointment/delete")
    public void deleteAppointment(AppointmentDTO appointmentDTO) {

    }
}
