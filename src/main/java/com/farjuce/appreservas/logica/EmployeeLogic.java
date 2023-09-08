package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.employee.EmployeeRepository;
import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeLogic {

    private EmployeeRepository employeeRepository;

    public EmployeeLogic(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setEmployee_id(employeeDTO.getEmployee_id());
        employee.setJob(employeeDTO.getName());
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
}
