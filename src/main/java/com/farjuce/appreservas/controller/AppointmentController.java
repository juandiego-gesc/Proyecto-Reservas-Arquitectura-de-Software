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

    @PostMapping(path = "/app/appointment/create")
    public String createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            return "Appointment created with id: " + logic.createAppointment(appointmentDTO).getAppointmentId();
        } catch (Exception e) {
            return "Appointment not created: " + e.getMessage();
        }
    }

    @GetMapping(path = "/app/getMyAppointment/{id}")
    public List<Appointment> getMyAppointment(@PathVariable Long id) {
        return logic.getMyAppointments(id);
    }

    @GetMapping(path = "/app/appointments/getAll")
    public List<Appointment> getAllAppointments() {
        return logic.getAllAppointments();
    }

    @GetMapping(path = "/app/appointments/getAvailable")
    public List<Employee> getAvailableAppointments(@RequestParam Long taskId, @RequestParam String startTime,
                                                   @RequestParam String endTime, @RequestParam String date) {
        return logic.getAvailabilityByTimeAndTask(taskId, startTime, endTime, date);
    }

    @PutMapping(path = "/app/appointment/update")
    public void updateAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        logic.updateAppointment(appointmentDTO);

    }

    @DeleteMapping(path = "/app/appointment/delete")
    public void deleteAppointment(AppointmentDTO appointmentDTO) {
        logic.cancelAppointment(appointmentDTO.getId());
    }
}
