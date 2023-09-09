package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeLogic {

    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository; // Assuming you have a TaskRepository

    public EmployeeLogic(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setTask(taskRepository.getById(employeeDTO.getTask_id()));

        //Todo: See if I can reduce coupling here

        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

