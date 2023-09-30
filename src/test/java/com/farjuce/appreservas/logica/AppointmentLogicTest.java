package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.appointment.AppointmentRepository;
import com.farjuce.appreservas.bd.brach.Branch;
import com.farjuce.appreservas.bd.brach.BranchRepository;
import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.*;
import com.farjuce.appreservas.logica.AppointmentLogic;
import com.farjuce.appreservas.logica.exception.AppointmentNotAvailableException;
import com.farjuce.appreservas.logica.exception.DuplicatedAppointmentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
class AppointmentLogicTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BranchRepository branchRepository;


    @InjectMocks
    private BranchLogic branchLogic;

    @InjectMocks
    private CustomerLogic customerLogic;

    @InjectMocks
    private TaskLogic taskLogic;

    @InjectMocks
    private EmployeeLogic employeeLogic;

    @InjectMocks
    AppointmentLogic appointmentLogic;

    @Test
    void Given_appointment_When_creating_new_appointment_Then_save() {

        EmployeeDTO employeeDTO = new EmployeeDTO("Employee Name", 1L);
        Employee employee = employeeLogic.addEmployee(employeeDTO);
        employee.setEmployee_id(1L);

        ArrayList<Employee> availableEmployees = new ArrayList<>();
        availableEmployees.add(employee);

        AppointmentDTO dto = new AppointmentDTO("2021-05-05",
                                                "10:00:00",
                                                "11:00:00",
                                                "Active",
                                                1L,
                                                1L,
                                                1L);

        when(appointmentLogic.getAvailabilityByTimeAndTask(dto.getTask_id(), dto.getStart_time(), dto.getEnd_time(), dto.getDate())).thenReturn(availableEmployees);

        appointmentLogic.createAppointment(dto);

        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    public void Given_no_employees_available_When_creating_new_appointment_Then_throw_AppointmentNotAvailableException() {
        AppointmentDTO appointmentDTO = new AppointmentDTO("2021-05-05",
            "10:00:00",
            "11:00:00",
            "Active",
            1L,
            1L,
            1L);

        List<Employee> availability = new ArrayList<>();


        when(appointmentRepository.findAvailableEmployees(any(), any(), any(), any())).thenReturn(new ArrayList<>());
        when(employeeRepository.findAllById(any())).thenReturn(availability);

        Assertions.assertThrows(AppointmentNotAvailableException.class, () -> appointmentLogic.createAppointment(appointmentDTO));
    }

    @Test
    void Given_task_id_start_time_end_time_and_date_When_getAvailabilityByTimeAndTask_Then_return_list_of_available_employees() {


        Long employeeId = 1L;
        List<Object[]> queryResult = new ArrayList<>();
        queryResult.add(new Object[] {employeeId});

        when(appointmentRepository.findAvailableEmployees(anyLong(), anyString(), anyString(), anyString())).thenReturn(queryResult);


        EmployeeDTO employeeDTO = new EmployeeDTO("Employee Name", 1L);
        Employee employee = employeeLogic.addEmployee(employeeDTO);
        employee.setEmployee_id(employeeId);


        when(employeeRepository.findAllById(List.of(employeeId))).thenReturn(List.of(employee));


        List<Employee> employees = appointmentLogic.getAvailabilityByTimeAndTask(1L, "10:00:00", "11:00:00", "2023-09-29");


        assertThat(employees).contains(employee);
    }

    @Test
    public void cancelAppointment_givenAnAppointmentId_whenCancelAppointmentIsCalled_thenAppointmentShouldBeDeleted() {

        Long appointmentId = 1L;


        appointmentLogic.cancelAppointment(appointmentId);


        verify(appointmentRepository).deleteById(appointmentId);
    }

    @Test
    public void update_appointment_Given_an_appointmentDTO_When_updateAppointment_is_called_Then_appointment_should_be_updated() {

        Appointment appointment = new Appointment();
        appointment.setAppointment_id(1L);

        when(appointmentRepository.getReferenceById(any())).thenReturn(appointment);
        when(appointmentRepository.save(any())).thenReturn(appointment);

        AppointmentDTO appointmentDTO = new AppointmentDTO("2021-05-05",
            "10:00:00",
            "11:00:00",
            "Active",
            1L,
            1L,
            1L);


        appointmentLogic.updateAppointment(appointmentDTO);


        Assertions.assertEquals("2021-05-05", appointment.getDate());
        Assertions.assertEquals("10:00:00", appointment.getStart_time());
        Assertions.assertEquals("11:00:00", appointment.getEnd_time());
    }

    @Test
    public void getAllAppointments_When_getAllAppointmentsIsCalled_Then_returnAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        appointments.add(new Appointment());

        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<Appointment> result = appointmentLogic.getAllAppointments();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getMyAppointments_Given_a_CustomerId_When_getMyAppointments_is_called_Then_return_my_appointments() {
        Long customerId = 1L;

        List<Appointment> myAppointments = appointmentLogic.getMyAppointments(customerId);

        List<Appointment> allAppointments = appointmentRepository.findAll();

        List<Appointment> expectedAppointments = new ArrayList<>();
        for (Appointment appointment : allAppointments) {
            if (Objects.equals(appointment.getCustomer().getCustomer_id(), customerId)) {
                expectedAppointments.add(appointment);
            }
        }

        assertEquals(expectedAppointments, myAppointments);
    }

    @Test
    public void getMyAppointments_When_IdIsNull_Then_return_empty_list() {

        Long id = null;

        List<Appointment> myAppointments = appointmentLogic.getMyAppointments(id);

        Assertions.assertNotNull(myAppointments);
        Assertions.assertEquals(0, myAppointments.size());
    }
}