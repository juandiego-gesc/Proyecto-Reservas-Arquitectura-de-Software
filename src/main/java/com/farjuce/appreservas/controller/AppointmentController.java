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

    @CrossOrigin(origins = "http://localhost:3000")

    @PostMapping(path = "/app/appointment/create")
    public String createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            Appointment appointment = logic.createAppointment(appointmentDTO);
            LOGGER.info("Appointment created with ID: {}", appointment.getAppointmentId());
            return "Appointment created with ID: " + appointment.getAppointmentId();
        } catch (Exception e) {
            LOGGER.error("Appointment not created: {}", e.getMessage());
            return "Appointment can't be created: " + e.getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")

    @GetMapping(path = "/app/getMyAppointment/{id}")
    public List<Appointment> getMyAppointment(@PathVariable Long id) {
        LOGGER.info("Getting appointments for customer with id: {}", id);
        return logic.getMyAppointments(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")

    @GetMapping(path = "/app/appointments/getAll")
    public List<Appointment> getAllAppointments() {
        LOGGER.info("Getting all appointments");
        return logic.getAllAppointments();
    }

    @CrossOrigin(origins = "http://localhost:3000")

    @GetMapping(path = "/app/appointments/getAvailable")
    public List<Employee> getAvailableAppointments(@RequestParam Long taskId, @RequestParam String startTime,
                                                   @RequestParam String endTime, @RequestParam String date) {
        LOGGER.info("Getting available appointments for task with id: {}, start time: {}, end time: {}, and date: {}", taskId, startTime, endTime, date);
        return logic.getAvailabilityByTimeAndTask(taskId, startTime, endTime, date);
    }

    @CrossOrigin(origins = "http://localhost:3000")

    @PutMapping(path = "/app/appointment/update")
    public String updateAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            LOGGER.info("Updating appointment with id: {}", appointmentDTO.getId());
            logic.updateAppointment(appointmentDTO);
            return ("Appointment with ID:" + appointmentDTO.getId() + " was updated \n" +
                    "Start Time: " + appointmentDTO.getStartTime() + "\n" +
                    "End Time: " + appointmentDTO.getEndTime() + "\n" +
                    "Date: " + appointmentDTO.getDate());
        } catch (Exception e) {
            return ("The Appointment can't be updated: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")

    @DeleteMapping(path = "/app/appointment/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        try {
            logic.cancelAppointment(id);
            LOGGER.info("Deleting appointment with ID: {}", id);
            return ("Appointment with ID: " + id + " was deleted");
        } catch (Exception e) {
            return ("The Appointment can't be deleted: " + e.getMessage());
        }
    }
}
