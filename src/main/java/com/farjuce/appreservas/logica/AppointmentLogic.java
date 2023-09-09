package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.appointment.AppointmentRepository;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentLogic {

    private AppointmentRepository appointmentRepository;

    private CustomerRepository customerRepository;

    private TaskRepository taskRepository;

    private EmployeeRepository employeeRepository;


    public AppointmentLogic(AppointmentRepository appointmentRepository, CustomerRepository customerRepository, TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    // Todo: Finish this

    public void createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentDTO.getDate());
        appointment.setStart_time(appointmentDTO.getStart_time());
        appointment.setEnd_time(appointmentDTO.getEnd_time());
        appointment.setState(appointmentDTO.getState());
        appointment.setCustomer(customerRepository.getById(appointmentDTO.getCustomer_id()));
        appointment.setEmployee(employeeRepository.getById(appointmentDTO.getEmployee_id()));
        appointment.setTask(taskRepository.getById(appointmentDTO.getTask_id()));

        appointmentRepository.save(appointment);
    }
    public Appointment relation(Long customer_id, Long appointment_id){
        Appointment appointment = appointmentRepository.findById(appointment_id).get();
        Customer customer = customerRepository.findById(customer_id).get();
        appointment.setCustomer(customer);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }
}
