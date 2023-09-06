package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.appointment.AppointmentRepository;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import org.springframework.stereotype.Service;

@Service
public class AppointmentLogic {

    private AppointmentRepository appointmentRepository;

    public AppointmentLogic(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Todo: Implement this class

    public void createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentDTO.getAppointmentDate());
        appointment.setEmployee(appointmentDTO.getEmployee());
        appointment.setService(appointmentDTO.getService());
        appointment.setUser(appointmentDTO.getUser());
        appointmentRepository.save(appointment);
    }
}
