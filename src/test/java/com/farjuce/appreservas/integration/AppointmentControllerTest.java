package com.farjuce.appreservas.integration;

import com.farjuce.appreservas.controller.dto.AppointmentDTO;
import com.farjuce.appreservas.controller.dto.CustomerDTO;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppointmentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void createAppointment() {

        RestTemplate mockRes = mock(RestTemplate.class);

        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L);

        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 2, 1));

        CustomerDTO customerDTO = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);

        AppointmentDTO appointmentDTO = new AppointmentDTO("2021-05-05",
                "10:00:00",
                "11:00:00",
                "Active",
                1L,
                1L,
                1L);

        restTemplate.postForEntity("/app/task/add", tasks, Boolean.class);
        restTemplate.postForEntity("/app/employee/add", employeeDTO, String.class);
        restTemplate.postForEntity("/app/customer/add", customerDTO, String.class);



        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("/app/appointment/create", appointmentDTO, String.class);
        ResponseEntity<List> forEntity = restTemplate.getForEntity("/app/appointments/getAll", List.class);
        System.out.println(forEntity.getBody());
    }
}