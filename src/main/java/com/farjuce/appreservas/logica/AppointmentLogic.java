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

    // Todo: Finish this

    public void createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
        appointment.setDate(appointmentDTO.getDate());
        appointment.setStartTime(appointmentDTO.setStatus(););

        appointmentRepository.save(appointment);
    }
}
