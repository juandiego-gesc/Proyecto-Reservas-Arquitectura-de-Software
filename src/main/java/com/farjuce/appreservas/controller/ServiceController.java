package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.controller.dto.ServiceDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {
    private ServiceLogic logic;

    public ServiceController(ServiceLogic logic) {
        this.logic = logic;
    }

    @GetMapping(path = "/services/getAll")
    public List<Employee> getEmployees() {
        return logic.getAllServices();
    }

    @PostMapping(path = "/service/add")
    public boolean addService(@RequestBody ServiceDTO serviceDTO){
        logic.addService(serviceDTO);
        return true;
    }
}

