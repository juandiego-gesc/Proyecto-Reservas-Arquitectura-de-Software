package com.farjuce.appreservas.integration;

import com.farjuce.appreservas.controller.dto.BranchDTO;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import com.farjuce.appreservas.controller.dto.TaskDTO;
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

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Given_task_branch_and_employee_When_added_employee_Then_employeeBD_is_not_empty() {
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 2, 1));
        restTemplate.postForEntity("/app/task/add", tasks, Boolean.class);

        BranchDTO branchDTO = new BranchDTO("Test", "TestAddress", "TestType", LocalTime.now(), LocalTime.now());
        restTemplate.postForEntity("/app/branch/add", branchDTO, Boolean.class);

        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L, 1L);
        restTemplate.postForEntity("/app/employee/add", employeeDTO, String.class);
        ResponseEntity<List> employeesDB = restTemplate.getForEntity("/app/employees/getAll", List.class);

        Assertions.assertFalse(employeesDB.getBody().isEmpty());
    }

    @Test
    void Given_tasks_branch_and_employees_When_added_employee_Then_retrieved_size_equals_employees_size() {
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("Task", "Test Task", 2, 1));
        tasks.add(new TaskDTO("Task1", "Test Task", 2, 1));
        BranchDTO branchDTO = new BranchDTO("Test", "TestAddress", "TestType", LocalTime.now(), LocalTime.now());

        ResponseEntity<Boolean> confirmation1 = restTemplate.postForEntity("/app/branch/add", branchDTO, Boolean.class);
        ResponseEntity<Boolean> confirmation2 = restTemplate.postForEntity("/app/task/add", tasks, Boolean.class);

        EmployeeDTO employeeDTO = new EmployeeDTO("Juan", 1L, 1L);
        EmployeeDTO employeeDTO1 = new EmployeeDTO("Pedro", 2L, 1L);
        restTemplate.postForEntity("/app/employee/add", employeeDTO, String.class);
        restTemplate.postForEntity("/app/employee/add", employeeDTO1, String.class);

        ResponseEntity<List> employeesDB = restTemplate.getForEntity("/app/employees/getAll", List.class);

        Assertions.assertEquals(2, employeesDB.getBody().size());
    }
}