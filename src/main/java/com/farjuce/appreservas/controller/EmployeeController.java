package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import com.farjuce.appreservas.logica.EmployeeLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeLogic logic;

    public EmployeeController(EmployeeLogic logic) {
        this.logic = logic;
    }

    @GetMapping(path = "/app/employees/getAll")
    public List<Employee> getAllEmployeesEmployees() {
        LOGGER.info("Getting all employees");
        List<Employee> employees = logic.getAllEmployees();
        LOGGER.debug("Retrieved {} employees", employees.size());
        return employees;
    }

    @PostMapping(path = "/app/employee/add")
    public boolean addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        LOGGER.info("Adding employee: {}", employeeDTO);
        logic.addEmployee(employeeDTO);
        LOGGER.debug("Employee added successfully");
        return true;
    }
}
