package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.brach.BranchRepository;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.task.TaskRepository;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeLogic {

    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;
    private BranchRepository branchRepository;

    public EmployeeLogic(EmployeeRepository employeeRepository, TaskRepository taskRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.branchRepository = branchRepository;
    }

    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setTask(taskRepository.getReferenceById(employeeDTO.getTaskId()));
        employee.setBranch(branchRepository.getReferenceById(employeeDTO.getBranchId()));
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
