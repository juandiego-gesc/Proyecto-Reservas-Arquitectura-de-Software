package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.appointment.AppointmentRepository;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.logica.AppointmentLogic;
import com.farjuce.appreservas.logica.exception.DuplicatedAppointmentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;

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

    @InjectMocks
    AppointmentLogic appointmentLogic;

    /*@Test
    void Given_appointment_booked_When_creating_new_appointment_Then_exception() {
        AppointmentDTO dto = new AppointmentDTO("2021-05-05",
                                                "10:00:00",
                                                "11:00:00",
                                                "Active",
                                                1L,
                                                1L,
                                                1L);


        Appointment appointment = appointmentLogic.createAppointment(dto);
        Assertions.assertThrows(DuplicatedAppointmentException.class, () -> appointmentLogic.createAppointment(dto));

    }

*/

    @Test
    void getAvailabilityByTimeAndTask() {
    }

    @Test
    void cancelAppointment() {
    }

    @Test
    void updateAppointment() {
    }

    @Test
    void relationCustomer() {
    }

    @Test
    void relationEmployee() {
    }

    @Test
    void relationTask() {
    }

    @Test
    void getAllAppointments() {
    }

    @Test
    void getMyAppointments() {
    }
}