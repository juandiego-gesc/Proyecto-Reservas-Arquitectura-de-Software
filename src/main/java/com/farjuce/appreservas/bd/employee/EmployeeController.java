package com.farjuce.appreservas.bd.employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/employee")
    public List<Employee> obtenerEmployee() {
        return employeeRepository.findAll();

    }

    @PostMapping(path = "/employee/add")
    public boolean addEmployee(@RequestBody Employee employee){
        this.employeeRepository.save(employee);
        return true;
    }
}

