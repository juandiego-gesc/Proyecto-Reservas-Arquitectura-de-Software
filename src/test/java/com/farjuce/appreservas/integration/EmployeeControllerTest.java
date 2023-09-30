package com.farjuce.appreservas.integration;

import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Given_task_and_employee_When_added_employee_Then_employeeBD_is_not_empty() {
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 2, 1));
        restTemplate.postForEntity("/task/add", tasks, Boolean.class);

        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L);
        restTemplate.postForEntity("/employee/add", employeeDTO, String.class);
        ResponseEntity<List> employeesDB = restTemplate.getForEntity("/employees/getAll", List.class);

        Assertions.assertFalse(employeesDB.getBody().isEmpty());
    }

    @Test
    void Given_tasks_and_employees_When_added_employee_Then_retrieved_size_equals_employees_size() {
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 2, 1));
        tasks.add(new TaskDTO("Task1", "Test Task", 2, 1));
        ResponseEntity<Boolean> confirmation = restTemplate.postForEntity("/task/add", tasks, Boolean.class);

        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L);
        EmployeeDTO employeeDTO1 = new EmployeeDTO("Pedro", 2L);
        restTemplate.postForEntity("/employee/add", employeeDTO, String.class);
        restTemplate.postForEntity("/employee/add", employeeDTO1, String.class);

        ResponseEntity<List> employeesDB = restTemplate.getForEntity("/employees/getAll", List.class);

        //3 because the first employee is added in the first Test
        Assertions.assertEquals(employeesDB.getBody().size(), 3);
    }


}