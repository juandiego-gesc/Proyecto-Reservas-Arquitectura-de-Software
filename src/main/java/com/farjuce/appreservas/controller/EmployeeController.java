package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.controller.dto.EmployeeDTO;
import com.farjuce.appreservas.logica.EmployeeLogic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeLogic logic;

    public EmployeeController(EmployeeLogic logic) {
        this.logic = logic;
    }

    @GetMapping(path = "/employees/getAll")
    public List<Employee> getAllEmployeesEmployees() {
        return logic.getAllEmployees();
    }

    @PostMapping(path = "/employee/add")
    public boolean addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        logic.addEmployee(employeeDTO);
        return true;
    }
}
