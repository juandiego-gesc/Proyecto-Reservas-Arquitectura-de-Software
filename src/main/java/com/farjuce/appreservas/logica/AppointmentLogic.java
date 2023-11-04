package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.appointment.AppointmentRepository;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.logica.exception.AppointmentNotAvailableException;
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

        List<Employee> availability = getAvailabilityByTimeAndTask(appointmentDTO.getTaskId(),
                appointmentDTO.getStartTime(), appointmentDTO.getEndTime(), dateInString);


        if (availability.stream().anyMatch(employee -> employee.getEmployeeId() == appointmentDTO.getEmployeeId())) {
            Appointment appointment = new Appointment();
            appointment.setDate(appointmentDTO.getDate());
            appointment.setStartTime(appointmentDTO.getStartTime());
            appointment.setEndTime(appointmentDTO.getEndTime());
            appointment.setState("Active");
            appointment.setCustomer(customerRepository.getReferenceById(appointmentDTO.getCustomerId()));
            appointment.setEmployee(employeeRepository.getReferenceById(appointmentDTO.getEmployeeId()));
            appointment.setTask(taskRepository.getReferenceById(appointmentDTO.getTaskId()));
            appointmentRepository.save(appointment);
            return appointment;
        } else {
            throw new AppointmentNotAvailableException();
        }
    }

    public List<Employee> getAvailabilityByTimeAndTask(Long taskId, String startTime, String endTime, String date) {
        List<Long> employeeIds = new ArrayList<>();

        List<Object[]> queryResult = appointmentRepository.findAvailableEmployees(taskId, date, startTime, endTime);

        for (Object[] result : queryResult) {
            Long employeeId = ((Number) result[0]).longValue();
            employeeIds.add(employeeId);
        }

        return employeeRepository.findAllById(employeeIds);
    }

    public void cancelAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public void updateAppointment(AppointmentDTO appointmentDTO) {

        Appointment appointment = appointmentRepository.getReferenceById(appointmentDTO.getId());
        appointment.setDate(appointmentDTO.getDate());
        appointment.setStartTime(appointmentDTO.getStartTime());
        appointment.setEndTime(appointmentDTO.getEndTime());
        appointmentRepository.save(appointment);


    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getMyAppointments(Long id) {

        List<Appointment> myAppointment = new ArrayList<>();
        List<Appointment> appointments = appointmentRepository.findAll();
        for (Appointment appointment : appointments) {

            if (Objects.equals(appointment.getCustomer().getCustomerId(), id)) {
                myAppointment.add(appointment);

            }
        }
        return myAppointment;
    }
}
