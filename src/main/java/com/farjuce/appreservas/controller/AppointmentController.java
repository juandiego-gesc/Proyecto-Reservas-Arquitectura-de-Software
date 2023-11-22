package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.logica.AppointmentLogic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
            LOGGER.info("Appointment created with id: {}", appointment.getAppointmentId());
            return "Appointment created with id: " + appointment.getAppointmentId();
        } catch (Exception e) {
            LOGGER.error("Appointment not created: {}", e.getMessage());
            return "Appointment not created: " + e.getMessage();
        }
    }

    @GetMapping(path = "/app/getMyAppointment/{id}")
    public List<Appointment> getMyAppointment(@PathVariable Long id) {
        LOGGER.info("Getting appointments for customer with id: {}", id);
        return logic.getMyAppointments(id);
    }

    @GetMapping(path = "/app/appointments/getAll")
    public List<Appointment> getAllAppointments() {
        LOGGER.info("Getting all appointments");
        return logic.getAllAppointments();
    }

    @GetMapping(path = "/app/appointments/getAvailable")
    public List<Employee> getAvailableAppointments(@RequestParam Long taskId, @RequestParam String startTime,
                                                   @RequestParam String endTime, @RequestParam String date) {
        LOGGER.info("Getting available appointments for task with id: {}, start time: {}, end time: {}, and date: {}", taskId, startTime, endTime, date);
        return logic.getAvailabilityByTimeAndTask(taskId, startTime, endTime, date);
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
