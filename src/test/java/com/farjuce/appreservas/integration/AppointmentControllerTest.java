package com.farjuce.appreservas.integration;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.controller.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import com.farjuce.appreservas.bd.appointment.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AppointmentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Given_task_employee_branch_and_customer_When_add_appointment_Then_check_if_its_created() {
        RestTemplate mockRes = mock(RestTemplate.class);
        BranchDTO branchDTO = new BranchDTO("Test Branch", "Test Address", "Test Type", LocalTime.parse("11:00:00"), LocalTime.parse("17:00:00"));
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 1, 1));
        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L, 1L);
        CustomerDTO customerDTO = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);

        restTemplate.postForEntity("/app/branch/add", branchDTO, Boolean.class);
        restTemplate.postForEntity("/app/task/add", tasks, Boolean.class);
        restTemplate.postForEntity("/app/employee/add", employeeDTO, String.class);
        restTemplate.postForEntity("/app/customer/add", customerDTO, String.class);

        AppointmentDTO appointmentDTO = new AppointmentDTO(LocalDate.now().toString(),
                "14:00:00",
                "15:00:00",
                "Active",
                1L,
                1L,
                1L);

        ResponseEntity<String> stringResponseEntityGood = restTemplate.postForEntity("/app/appointment/create", appointmentDTO, String.class);
        ResponseEntity<String> stringResponseEntityBad = restTemplate.postForEntity("/app/appointment/create", appointmentDTO, String.class);

        Assertions.assertTrue(stringResponseEntityGood.getBody().contains("Appointment created"));
        Assertions.assertTrue(stringResponseEntityBad.getBody().contains("Appointment can't be created"));
    }

    @Test
    void Given_appointments_When_search_with_a_costumer_id_Then_return_only_the_customer_appointments() throws JsonProcessingException {
        RestTemplate mockRes = mock(RestTemplate.class);

        BranchDTO branchDTO = new BranchDTO("Test Branch", "Test Address", "Test Type", LocalTime.parse("11:00:00"), LocalTime.parse("17:00:00"));
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 1, 1));
        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L, 1L);
        CustomerDTO customerDTO1 = new CustomerDTO("Pepe", "pepe@unisabana.edu.co", 3L);
        CustomerDTO customerDTO2 = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);

        restTemplate.postForEntity("/app/branch/add", branchDTO, Boolean.class);
        restTemplate.postForEntity("/app/task/add", tasks, Boolean.class);
        restTemplate.postForEntity("/app/employee/add", employeeDTO, String.class);
        restTemplate.postForEntity("/app/customer/add", customerDTO1, String.class);
        restTemplate.postForEntity("/app/customer/add", customerDTO2, String.class);

        AppointmentDTO appointmentDTO1 = new AppointmentDTO(LocalDate.now().toString(),
                "14:00:00",
                "15:00:00",
                "Active",
                1L,
                1L,
                1L);
        AppointmentDTO appointmentDTO2 = new AppointmentDTO(LocalDate.now().toString(),
                "15:00:00",
                "16:00:00",
                "Active",
                2L,
                1L,
                1L);

        restTemplate.postForEntity("/app/appointment/create", appointmentDTO1, String.class);
        restTemplate.postForEntity("/app/appointment/create", appointmentDTO2, String.class);
        ResponseEntity<String> stringResponseEntity = restTemplate.getForEntity("/app/getMyAppointment/" + 1L, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Appointment> receivedAppointments = objectMapper.readValue(stringResponseEntity.getBody(), new TypeReference<List<Appointment>>() {
        });

        Assertions.assertEquals(1L, receivedAppointments.get(0).getCustomer().getCustomerId());
        Assertions.assertEquals(receivedAppointments.get(0).getStartTime(), appointmentDTO1.getStartTime());
        Assertions.assertEquals(receivedAppointments.get(0).getEndTime(), appointmentDTO1.getEndTime());
        Assertions.assertEquals(receivedAppointments.get(0).getDate(), appointmentDTO1.getDate());
        Assertions.assertEquals(1, receivedAppointments.size());
    }

    @Test
    void Given_appointments_When_search_all_appointments_Then_return_all_the_appointments() throws JsonProcessingException {
        RestTemplate mockRes = mock(RestTemplate.class);

        BranchDTO branchDTO = new BranchDTO("Test Branch", "Test Address", "Test Type", LocalTime.parse("11:00:00"), LocalTime.parse("17:00:00"));
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 1, 1));
        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L, 1L);
        CustomerDTO customerDTO = new CustomerDTO("Pepe", "pepe@unisabana.edu.co", 3L);

        restTemplate.postForEntity("/app/branch/add", branchDTO, Boolean.class);
        restTemplate.postForEntity("/app/task/add", tasks, Boolean.class);
        restTemplate.postForEntity("/app/employee/add", employeeDTO, String.class);
        restTemplate.postForEntity("/app/customer/add", customerDTO, String.class);

        AppointmentDTO appointmentDTO = new AppointmentDTO(LocalDate.now().toString(),
                "14:00:00",
                "15:00:00",
                "Active",
                1L,
                1L,
                1L);

        List<AppointmentDTO> newAppointments = new ArrayList<>();
        newAppointments.add(appointmentDTO);

        int expectedAppointments = newAppointments.size();

        restTemplate.postForEntity("/app/appointment/create", appointmentDTO, String.class);
        ResponseEntity<String> stringResponseEntity = restTemplate.getForEntity("/app/appointments/getAll", String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Appointment> receivedAppointments = objectMapper.readValue(stringResponseEntity.getBody(), new TypeReference<List<Appointment>>() {
        });
        Assertions.assertEquals(expectedAppointments, receivedAppointments.size());

        for (int indexList = 0; indexList < newAppointments.size(); indexList++) {
            Assertions.assertEquals(receivedAppointments.get(indexList).getCustomer().getCustomerId(), newAppointments.get(indexList).getCustomerId());
            Assertions.assertEquals(receivedAppointments.get(indexList).getStartTime(), newAppointments.get(indexList).getStartTime());
            Assertions.assertEquals(receivedAppointments.get(indexList).getEndTime(), newAppointments.get(indexList).getEndTime());
            Assertions.assertEquals(receivedAppointments.get(indexList).getDate(), newAppointments.get(indexList).getDate());
        }
    }

    @Test
    void Given_appointment_When_delete_the_appointment_Then_confirm_if_was_deleted() throws JsonProcessingException {
        RestTemplate mockRes = mock(RestTemplate.class);

        BranchDTO branchDTO = new BranchDTO("Test Branch", "Test Address", "Test Type", LocalTime.parse("11:00:00"), LocalTime.parse("17:00:00"));
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 1, 1));
        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L, 1L);
        CustomerDTO customerDTO = new CustomerDTO("Pepe", "pepe@unisabana.edu.co", 3L);

        restTemplate.postForEntity("/app/branch/add", branchDTO, Boolean.class);
        restTemplate.postForEntity("/app/task/add", tasks, Boolean.class);
        restTemplate.postForEntity("/app/employee/add", employeeDTO, String.class);
        restTemplate.postForEntity("/app/customer/add", customerDTO, String.class);

        AppointmentDTO appointmentDTO = new AppointmentDTO(LocalDate.now().toString(),
                "14:00:00",
                "15:00:00",
                "Active",
                1L,
                1L,
                1L);

        restTemplate.postForEntity("/app/appointment/create", appointmentDTO, String.class);
        int expectedAppointmentsInitial = 1;
        ResponseEntity<List> receivedAppointmentsBeforeDeleteDB = restTemplate.getForEntity("/app/appointments/getAll", List.class);
        int receivedAppointmentsBeforeDelete = receivedAppointmentsBeforeDeleteDB.getBody().size();

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> stringResponseEntity = restTemplate.getForEntity("/app/appointments/getAll", String.class);
        List<Appointment> receivedAppointments = objectMapper.readValue(stringResponseEntity.getBody(), new TypeReference<List<Appointment>>() {
        });
        long appointmentID = receivedAppointments.get(0).getAppointmentId();

        restTemplate.delete("/app/appointment/delete/" + appointmentID, String.class);
        int expectedAppointmentsFinal = 0;
        ResponseEntity<List> receivedAppointmentsAfterDeleteDB = restTemplate.getForEntity("/app/appointments/getAll/", List.class);
        int receivedAppointmentsAfterDelete = receivedAppointmentsAfterDeleteDB.getBody().size();

        Assertions.assertEquals(expectedAppointmentsInitial, receivedAppointmentsBeforeDelete);
        Assertions.assertEquals(expectedAppointmentsFinal, receivedAppointmentsAfterDelete);
    }

    @Test
    void Given_task_start_and_end_time_and_date_When_search_avaiability_Then_return_avaiable_employees() throws JsonProcessingException {
        RestTemplate mockRes = mock(RestTemplate.class);

        BranchDTO branchDTO = new BranchDTO("Test Branch", "Test Address", "Test Type", LocalTime.parse("11:00:00"), LocalTime.parse("17:00:00"));
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 1, 1));
        EmployeeDTO employeeDTO1 = new EmployeeDTO("Juan", 1L, 1L);
        EmployeeDTO employeeDTO2 = new EmployeeDTO("Juan", 1L, 1L);
        CustomerDTO customerDTO = new CustomerDTO("Pepe", "pepe@unisabana.edu.co", 3L);

        restTemplate.postForEntity("/app/branch/add", branchDTO, Boolean.class);
        restTemplate.postForEntity("/app/task/add", tasks, Boolean.class);
        restTemplate.postForEntity("/app/employee/add", employeeDTO1, String.class);
        restTemplate.postForEntity("/app/employee/add", employeeDTO2, String.class);
        restTemplate.postForEntity("/app/customer/add", customerDTO, String.class);

        AppointmentDTO appointmentDTO = new AppointmentDTO(LocalDate.now().toString(),
                "14:00:00",
                "15:00:00",
                "Active",
                1L,
                2L,
                1L);

        restTemplate.postForEntity("/app/appointment/create", appointmentDTO, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> stringResponseEntity = restTemplate.getForEntity("/app/appointments/getAvailable?taskId=1&date=" + LocalDate.now().toString() + "&startTime=14:00:00&endTime=15:00:00", String.class);
        List<Employee> receivedAvaiableEmployees = objectMapper.readValue(stringResponseEntity.getBody(), new TypeReference<List<Employee>>() {
        });
        int receivedAvaiableEmployeesNumber = receivedAvaiableEmployees.size();
        int expectedEmployees = 1;

        Assertions.assertEquals(receivedAvaiableEmployeesNumber, expectedEmployees);
        Assertions.assertEquals(1, receivedAvaiableEmployees.get(0).getEmployeeId());
    }

    @Test
    void Given_appointment_When_update_appointment_Then_validate_if_update() throws JsonProcessingException, InterruptedException {
        RestTemplate mockRes = mock(RestTemplate.class);

        BranchDTO branchDTO = new BranchDTO("Test Branch", "Test Address", "Test Type", LocalTime.parse("11:00:00"), LocalTime.parse("17:00:00"));
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 1, 1));
        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L, 1L);
        CustomerDTO customerDTO = new CustomerDTO("Pepe", "pepe@unisabana.edu.co", 3L);

        restTemplate.postForEntity("/app/branch/add", branchDTO, Boolean.class);
        restTemplate.postForEntity("/app/task/add", tasks, Boolean.class);
        restTemplate.postForEntity("/app/employee/add", employeeDTO, String.class);
        restTemplate.postForEntity("/app/customer/add", customerDTO, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        AppointmentDTO appointmentDTO = new AppointmentDTO(LocalDate.now().toString(),
                "14:00:00",
                "15:00:00",
                "Active",
                1L,
                1L,
                1L);
        restTemplate.postForEntity("/app/appointment/create", appointmentDTO, String.class);
        ResponseEntity<String> stringResponseEntityBeforeUpdate = restTemplate.getForEntity("/app/appointments/getAll", String.class);
        List<Appointment> receivedOldAppointments = objectMapper.readValue(stringResponseEntityBeforeUpdate.getBody(), new TypeReference<List<Appointment>>() {
        });
        int initialAppointments = receivedOldAppointments.size();

        appointmentDTO.setId(1L);
        appointmentDTO.setStartTime("16:00:00");
        appointmentDTO.setEndTime("17:00:00");

        restTemplate.put("/app/appointment/update", appointmentDTO, String.class);
        ResponseEntity<String> stringResponseEntityAfterUpdate = restTemplate.getForEntity("/app/appointments/getAll", String.class);
        List<Appointment> receivedNewAppointments = objectMapper.readValue(stringResponseEntityAfterUpdate.getBody(), new TypeReference<List<Appointment>>() {
        });
        int finalAppointments = receivedNewAppointments.size();

        Assertions.assertEquals(finalAppointments, initialAppointments);
        Assertions.assertSame(receivedNewAppointments.get(0).getAppointmentId(), receivedOldAppointments.get(0).getAppointmentId());
        Assertions.assertEquals("16:00:00", receivedNewAppointments.get(0).getStartTime());
        Assertions.assertEquals("17:00:00", receivedNewAppointments.get(0).getEndTime());
    }
}