package com.farjuce.appreservas.integration;

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
class TaskControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Given_task_When_add_task_Then_check_taskDB_is_not_empty() {
        List<TaskDTO> tasks = new ArrayList<>();
        TaskDTO taskDTO = new TaskDTO("Task", "Test Task", 2, 1);
        tasks.add(taskDTO);

        ResponseEntity<Boolean> taskCreation = restTemplate.postForEntity("/task/add", tasks, Boolean.class);

        ResponseEntity<List> tasksDB = restTemplate.getForEntity("/task/getAll", List.class);

        Assertions.assertTrue(taskCreation.getBody());

        Assertions.assertFalse(tasksDB.getBody().isEmpty());
    }

    
}