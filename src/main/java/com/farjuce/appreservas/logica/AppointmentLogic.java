package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.appointment.AppointmentRepository;
import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import org.springframework.stereotype.Service;

@Service
public class AppointmentLogic {

    private AppointmentRepository appointmentRepository;

    private CustomerRepository customerRepository;


    public AppointmentLogic(AppointmentRepository appointmentRepository, CustomerRepository customerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
    }

    // Todo: Finish this

    public void createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointment_id(appointmentDTO.getAppointment_id());
        appointment.setDate(appointmentDTO.getDate());
        appointment.setStart_time(appointmentDTO.getStart_time());
        appointment.setEnd_time(appointmentDTO.getEnd_time());
        appointment.setState(appointmentDTO.getState());
        appointment.setCustomer(appointmentDTO.getCustomer());

        appointmentRepository.save(appointment);
    }
    public Appointment relation(Long customer_id, Long appointment_id){
        Appointment appointment = appointmentRepository.findById(appointment_id).get();
        Customer customer = customerRepository.findById(customer_id).get();
        appointment.setCustomer(customer);

        return appointmentRepository.save(appointment);
    }
}
