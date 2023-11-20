package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import com.farjuce.appreservas.logica.TaskLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private TaskLogic logic;

    public TaskController(TaskLogic logic) {
        this.logic = logic;
    }

    @PostMapping(path = "/app/task/add")
    public boolean saveTask(@RequestBody List<TaskDTO> taskDTOs) {
        logger.info("Received request to add task(s): {}", taskDTOs);
        logic.addTask(taskDTOs);
        logger.info("Task(s) added successfully");
        return true;
    }

    @GetMapping(path = "/app/task/getAll")
    public List<Task> getTask() {
        logger.info("Received request to get all tasks");
        List<Task> tasks = logic.getAllTasks();
        logger.info("Returning {} tasks", tasks.size());
        return tasks;
    }

}
