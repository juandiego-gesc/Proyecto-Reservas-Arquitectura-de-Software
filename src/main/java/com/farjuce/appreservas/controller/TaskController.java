package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import com.farjuce.appreservas.controller.dto.CustomerDTO;
import com.farjuce.appreservas.logica.TaskLogic;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    //Get all employees for the task
    @GetMapping(path = "/task/employeesForTask")
    public Set<Employee> getEmployeesForTask(@RequestParam Long taskId) {
        return logic.getEmployeesForTask(taskId);
    }



}

