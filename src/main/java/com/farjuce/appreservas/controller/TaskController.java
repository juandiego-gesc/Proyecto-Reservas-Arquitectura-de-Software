package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import com.farjuce.appreservas.controller.dto.CustomerDTO;
import com.farjuce.appreservas.logica.TaskLogic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private TaskLogic logic;

    public TaskController(TaskLogic logic) {
        this.logic = logic;
    }

    @PostMapping(path = "/task/add")
    public boolean saveTask(@RequestBody TaskDTO taskDTO){
        logic.addTask(taskDTO);
        return true;
    }

    @GetMapping(path = "/task/getAll")
    public List<Task> getTask() {
        return logic.getAllTasks();
    }


}

