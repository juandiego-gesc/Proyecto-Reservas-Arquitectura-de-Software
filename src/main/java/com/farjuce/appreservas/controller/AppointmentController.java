package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.logica.AppointmentLogic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    private AppointmentLogic logic;

    public AppointmentController(AppointmentLogic logicAppointment) {
        this.logic = logicAppointment;
    }

    @PostMapping(path = "/app/appointment/create")
    public String createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            Appointment appointment = logic.createAppointment(appointmentDTO);
            LOGGER.info("Appointment created with id: {}", appointment.getAppointment_id());
            return "Appointment created with id: " + appointment.getAppointment_id();
        } catch (Exception e) {
            LOGGER.error("Appointment not created: {}", e.getMessage());
            return "Appointment not created: " + e.getMessage();
        }
    }

    @GetMapping(path = "/app/getMyAppointment/{id}")
    public List<Appointment> getMyAppointment(@PathVariable Long id) {
        LOGGER.info("Getting appointments for employee with id: {}", id);
        return logic.getMyAppointments(id);
    }

    @GetMapping(path = "/app/appointments/getAll")
    public List<Appointment> getAllAppointments() {
        LOGGER.info("Getting all appointments");
        return logic.getAllAppointments();
    }

    @GetMapping(path = "/app/appointments/getAvailable")
    public List<Employee> getAvailableAppointments(@RequestParam Long task_id, @RequestParam String start_time,
            @RequestParam String end_time, @RequestParam String date) {
        LOGGER.info("Getting available appointments for task with id: {}, start time: {}, end time: {}, and date: {}", task_id, start_time, end_time, date);
        return logic.getAvailabilityByTimeAndTask(task_id, start_time, end_time, date);
    }

    @PutMapping(path = "/app/appointment/update")
    public void updateAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        LOGGER.info("Updating appointment with id: {}", appointmentDTO.getId());
        logic.updateAppointment(appointmentDTO);

    }

    @DeleteMapping(path = "/app/appointment/delete")
    public void deleteAppointment(AppointmentDTO appointmentDTO) {
        LOGGER.info("Deleting appointment with id: {}", appointmentDTO.getId());
    }
}
