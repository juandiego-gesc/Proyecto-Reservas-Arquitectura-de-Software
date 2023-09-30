package com.farjuce.appreservas.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppointmentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

//    @Test
//    void createAppointment() {
//
//        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L);
//
//        List<TaskDTO> tasks = new ArrayList<>();
//        tasks.add(new TaskDTO("Task", "Test Task", 2, 1));
//
//        CustomerDTO customerDTO = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);
//
//        AppointmentDTO appointmentDTO = new AppointmentDTO(
//                "2021-05-05",
//                "18:00:00",
//                "20:00:00",
//                "Active",
//                1L,
//                1L  ,
//                1L);
//
//        restTemplate.postForEntity("/task/add", tasks, Boolean.class);
//        restTemplate.postForEntity("/employee/add", employeeDTO, String.class);
//        restTemplate.postForEntity("/customer/add", customerDTO, String.class);
//
//        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("/appointment/create", appointmentDTO, String.class);
//
//        System.out.println("APPOINTMENT"+stringResponseEntity.getBody());
//    }

    @Test
    void getMyAppointment() {
    }

    @Test
    void getAllAppointments() {
    }

    @Test
    void getAvailableAppointments() {
    }

    @Test
    void updateAppointment() {
    }

    @Test
    void deleteAppointment() {
    }
}