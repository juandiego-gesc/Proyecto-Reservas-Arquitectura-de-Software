package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.appointment.AppointmentRepository;
import com.farjuce.appreservas.bd.employee.Employee;
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
    private EmployeeRepository employeeRepository;

    private TaskRepository taskRepository;


    public AppointmentLogic(AppointmentRepository appointmentRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    public void createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentDTO.getDate());
        appointment.setStart_time(appointmentDTO.getStart_time());
        appointment.setEnd_time(appointmentDTO.getEnd_time());
        appointment.setState(appointmentDTO.getState());
        appointment.setCustomer(customerRepository.getReferenceById(appointmentDTO.getCustomer_id()));
        appointment.setEmployee(employeeRepository.getReferenceById(appointmentDTO.getEmployee_id()));
        appointment.setTask(taskRepository.getReferenceById(appointmentDTO.getTask_id()));

        appointmentRepository.save(appointment);
    }
    public void cancelAppointment(Long id){

        appointmentRepository.deleteById(id);

    }

    public Appointment relationCustomer(Long customer_id, Long appointment_id){

        Appointment appointment = appointmentRepository.findById(appointment_id).get();
        Customer customer = customerRepository.findById(customer_id).get();
        appointment.setCustomer(customer);
        return appointmentRepository.save(appointment);
    }
    public Appointment relationEmployee(Long employee_id, Long appointment_id){

        Appointment appointment = appointmentRepository.findById(appointment_id).get();
        Employee employee = employeeRepository.findById(employee_id).get();
        appointment.setEmployee(employee);
        return appointmentRepository.save(appointment);
    }

    public Appointment relationTask(Long task_id, Long appointment_id){

        Appointment appointment = appointmentRepository.findById(appointment_id).get();
        Task task = taskRepository.findById(task_id).get();
        appointment.setTask(task);
        return appointmentRepository.save(appointment);

    }




    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }
}
