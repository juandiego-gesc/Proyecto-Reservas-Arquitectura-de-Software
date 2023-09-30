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
import com.farjuce.appreservas.logica.exception.AppointmentNotAvailableException;
import com.farjuce.appreservas.logica.exception.DuplicatedAppointmentException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AppointmentLogic {

    private AppointmentRepository appointmentRepository;

    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    private TaskRepository taskRepository;

    public AppointmentLogic(AppointmentRepository appointmentRepository, CustomerRepository customerRepository,
            TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public Appointment createAppointment(AppointmentDTO appointmentDTO) {


        String dateInString = appointmentDTO.getDate().toString();
        List<Employee> availability = getAvailabilityByTimeAndTask(appointmentDTO.getTask_id(),
                appointmentDTO.getStart_time(), appointmentDTO.getEnd_time(), dateInString);

        if (availability.stream().anyMatch(employee -> employee.getEmployee_id() == appointmentDTO.getEmployee_id())) {
            Appointment appointment = new Appointment();
            appointment.setDate(appointmentDTO.getDate());
            appointment.setStart_time(appointmentDTO.getStart_time());
            appointment.setEnd_time(appointmentDTO.getEnd_time());
            appointment.setState("Active");
            appointment.setCustomer(customerRepository.getReferenceById(appointmentDTO.getCustomer_id()));
            appointment.setEmployee(employeeRepository.getReferenceById(appointmentDTO.getEmployee_id()));
            appointment.setTask(taskRepository.getReferenceById(appointmentDTO.getTask_id()));
            appointmentRepository.save(appointment);
            return appointment;
        } else {
            throw new AppointmentNotAvailableException();
        }
    }

    public List<Employee> getAvailabilityByTimeAndTask(Long task_id, String start_time, String end_time, String date) {
        List<Object[]> queryResult = appointmentRepository.findAvailableEmployees(task_id, date, start_time, end_time);

        List<Long> employeeIds = new ArrayList<>();
        for (Object[] result : queryResult) {
            Long employeeId = ((Number) result[0]).longValue();
            employeeIds.add(employeeId);
        }
        return employeeRepository.findAllById(employeeIds);
    }

    public void cancelAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public void updateAppointment(AppointmentDTO appointmentDTO)  {

            Appointment appointment = appointmentRepository.getReferenceById(appointmentDTO.getId());
            appointment.setDate(appointmentDTO.getDate());
            appointment.setStart_time(appointmentDTO.getStart_time());
            appointment.setEnd_time(appointmentDTO.getEnd_time());
            appointmentRepository.save(appointment);


    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getMyAppointments(Long id) {

        List<Appointment> myAppointment = new ArrayList<>();
        List<Appointment> appointments = appointmentRepository.findAll();
        for (Appointment appointment : appointments) {

            if (Objects.equals(appointment.getCustomer().getCustomer_id(), id)) {
                myAppointment.add(appointment);

            }
        }
        return myAppointment;
    }
}
