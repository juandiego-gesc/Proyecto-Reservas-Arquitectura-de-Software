package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.appointment.AppointmentRepository;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentLogic {

    private AppointmentRepository appointmentRepository;

    public AppointmentLogic(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Todo: Finish this

    public void createAppointment(AppointmentDTO appointmentDTO) {
        /*Appointment appointment = new Appointment();
        appointment.setAppointment_id(appointmentDTO.getAppointmentId());
        appointment.setDate(appointmentDTO.getAppointmentDate());
        appointment.setStart_time(appointmentDTO.getAppointmentStatus());

        appointmentRepository.save(appointment);*/
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }
}
