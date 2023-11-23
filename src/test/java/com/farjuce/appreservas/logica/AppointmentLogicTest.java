package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.appointment.AppointmentRepository;
import com.farjuce.appreservas.bd.brach.BranchRepository;
import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.*;
import com.farjuce.appreservas.logica.exception.AppointmentNotAvailableException;
import com.farjuce.appreservas.logica.exception.CanNotUpdateAppointmentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AppointmentLogicTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private BranchRepository branchRepository;
    @InjectMocks
    private EmployeeLogic employeeLogic;
    @InjectMocks
    AppointmentLogic appointmentLogic;

    @Test
    void Given_appointment_When_creating_new_appointment_Then_save() {
        EmployeeDTO employeeDTO = new EmployeeDTO("Employee Name", 1L, 1L);
        Employee employee = employeeLogic.addEmployee(employeeDTO);
        employee.setEmployeeId(1L);

        ArrayList<Employee> availableEmployees = new ArrayList<>();
        availableEmployees.add(employee);

        AppointmentDTO dto = new AppointmentDTO("2021-05-05",
                "10:00:00",
                "11:00:00",
                "Active",
                1L,
                1L,
                1L);

        when(appointmentLogic.getAvailabilityByTimeAndTask(dto.getTaskId(), dto.getStartTime(), dto.getEndTime(), dto.getDate())).thenReturn(availableEmployees);
        appointmentLogic.createAppointment(dto);
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void Given_no_employees_available_When_creating_new_appointment_Then_throw_AppointmentNotAvailableException() {
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
        queryResult.add(new Object[]{employeeId});

        when(appointmentRepository.findAvailableEmployees(anyLong(), anyString(), anyString(), anyString())).thenReturn(queryResult);

        EmployeeDTO employeeDTO = new EmployeeDTO("Employee Name", 1L, 1L);
        Employee employee = employeeLogic.addEmployee(employeeDTO);
        employee.setEmployeeId(employeeId);

        when(employeeRepository.findAllById(List.of(employeeId))).thenReturn(List.of(employee));
        List<Employee> employees = appointmentLogic.getAvailabilityByTimeAndTask(1L, "10:00:00", "11:00:00", "2023-09-29");
        assertThat(employees).contains(employee);
    }

    @Test
    void cancelAppointment_givenAnAppointmentId_whenCancelAppointmentIsCalled_thenAppointmentShouldBeDeleted() {
        Long appointmentId = 1L;
        appointmentLogic.cancelAppointment(appointmentId);
        verify(appointmentRepository).deleteById(appointmentId);
    }

    @Test
    void update_appointment_Given_an_appointmentDTO_When_updateAppointment_is_called_Then_appointment_should_be_updated() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1L);
        EmployeeDTO employeeDTO = new EmployeeDTO("Employee Name", 1L, 1L);
        Employee employee = employeeLogic.addEmployee(employeeDTO);
        employee.setEmployeeId(1L);

        ArrayList<Employee> availableEmployees = new ArrayList<>();
        availableEmployees.add(employee);

        when(appointmentRepository.getReferenceById(any())).thenReturn(appointment);
        when(appointmentRepository.save(any())).thenReturn(appointment);

        AppointmentDTO appointmentDTO = new AppointmentDTO("2021-05-05",
                "08:00:00",
                "09:00:00",
                "Active",
                1L,
                1L,
                1L);

        when(appointmentLogic.getAvailabilityByTimeAndTask(appointmentDTO.getTaskId(), appointmentDTO.getStartTime(), appointmentDTO.getEndTime(), appointmentDTO.getDate())).thenReturn(availableEmployees);
        appointmentLogic.createAppointment(appointmentDTO);

        appointmentDTO.setStartTime("10:00:00");
        appointmentDTO.setEndTime("11:00:00");
        appointmentLogic.updateAppointment(appointmentDTO);

        Assertions.assertEquals("2021-05-05", appointment.getDate());
        Assertions.assertEquals("10:00:00", appointment.getStartTime());
        Assertions.assertEquals("11:00:00", appointment.getEndTime());
    }

    @Test
    void update_appointment_Given_an_appointmentDTO_When_updateAppointment_is_called_but_are_another_appointment_Then_appointment_should_return_exception() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1L);
        EmployeeDTO employeeDTO = new EmployeeDTO("Employee Name", 1L, 1L);
        Employee employee = employeeLogic.addEmployee(employeeDTO);
        employee.setEmployeeId(1L);

        ArrayList<Employee> availableEmployees = new ArrayList<>();
        availableEmployees.add(employee);
        when(appointmentRepository.save(any())).thenReturn(appointment);
        AppointmentDTO appointmentDTO = new AppointmentDTO("2021-05-05",
                "08:00:00",
                "09:00:00",
                "Active",
                1L,
                1L,
                1L);

        when(appointmentLogic.getAvailabilityByTimeAndTask(appointmentDTO.getTaskId(), appointmentDTO.getStartTime(), appointmentDTO.getEndTime(), appointmentDTO.getDate())).thenReturn(availableEmployees);
        appointmentLogic.createAppointment(appointmentDTO);
        ArrayList<Employee> availableEmployeesUpdated = new ArrayList<>();
        when(appointmentLogic.getAvailabilityByTimeAndTask(appointmentDTO.getTaskId(), appointmentDTO.getStartTime(), appointmentDTO.getEndTime(), appointmentDTO.getDate())).thenReturn(availableEmployeesUpdated);
        appointmentDTO.setStartTime("10:00:00");
        appointmentDTO.setEndTime("11:00:00");
        Assertions.assertThrows(CanNotUpdateAppointmentException.class, () -> appointmentLogic.updateAppointment(appointmentDTO));
    }

    @Test
    void getAllAppointments_When_getAllAppointmentsIsCalled_Then_returnAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        appointments.add(new Appointment());

        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<Appointment> result = appointmentLogic.getAllAppointments();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void getMyAppointments_Given_a_CustomerId_When_getMyAppointments_is_called_Then_return_my_appointments() {
        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);
        customer1.setName("Pepe");
        customer1.setEmail("pepe@test.com");
        customer1.setPhoneNumber(1234567890);

        Customer customer2 = new Customer();
        customer2.setCustomerId(2L);
        customer2.setName("Juan");
        customer2.setEmail("juan@test.com");
        customer2.setPhoneNumber(1234567890);

        Appointment appointment1 = new Appointment();
        appointment1.setCustomer(customer1);
        Appointment appointment2 = new Appointment();
        appointment2.setCustomer(customer2);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);

        List<Appointment> expectedAppointments = new ArrayList<>();
        expectedAppointments.add(appointment1);

        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<Appointment> myAppointments = appointmentLogic.getMyAppointments(customer1.getCustomerId());

        assertEquals(expectedAppointments, myAppointments);
    }

    @Test
    void getMyAppointments_When_IdIsNull_Then_return_empty_list() {
        Long id = null;

        List<Appointment> myAppointments = appointmentLogic.getMyAppointments(id);

        Assertions.assertNotNull(myAppointments);
        Assertions.assertEquals(0, myAppointments.size());
    }
}