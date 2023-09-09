package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.logica.AppointmentLogic;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class AppointmentController {

    private AppointmentLogic logic;

    public AppointmentController(AppointmentLogic logicAppointment) {
        this.logic = logicAppointment;
    }

    @GetMapping(path = "/appointments/getAvailabilityByTimeAndTask")
    public List<Employee> getAvailabilityByTimeAndTask(@RequestParam Long task_id, @RequestParam String start_time, @RequestParam String end_time, @RequestParam String date) {
        return logic.getAvailabilityByTimeAndTask(task_id, start_time, end_time, date);
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

    @PutMapping(path = "/appointment/update")
    public void updateAppointment(@RequestBody AppointmentDTO appointmentDTO) throws Exception {
        logic.updateAppointment(appointmentDTO);

    }

    @DeleteMapping(path = "/appointment/delete")
    public void deleteAppointment (@RequestParam Long id){
        logic.cancelAppointment(id);

    }
    @PutMapping("/{task_id}/appointment/{appointment_id}")
    public Appointment TaskAppointment(
            @PathVariable Long task_id,
            @PathVariable Long appointment_id)
    {
        return logic.relationCustomer(task_id, appointment_id);
    }



}
