package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import com.farjuce.appreservas.logica.TaskLogic;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private TaskLogic logic;

    public TaskController(TaskLogic logic) {
        this.logic = logic;
    }

    @PostMapping(path = "/tasks/add")
    public boolean saveTask(@RequestBody List<TaskDTO> taskDTOs) {
        logic.addTask(taskDTOs);
        return true;
    }

    @GetMapping(path = "/task/getAll")
    public List<Task> getTask() {
        return logic.getAllTasks();
    }

}
