package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.customer.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.farjuce.appreservas.controller.dto.CustomerDTO;
import com.farjuce.appreservas.logica.CustomerLogic;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerLogic logic;

    public CustomerController(CustomerLogic logicCustomer) {
        this.logic = logicCustomer;
    }

    @PostMapping(path = "/app/customer/add")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {

        String message = "Customer created";
        logic.addCustomer(customerDTO);

        return message;
    }

    @GetMapping(path = "/app/customer/getAll")
    public List<Customer> getCustomer() {
        return logic.getAllCustomers();
    }

}
