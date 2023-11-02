package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.farjuce.appreservas.controller.dto.CustomerDTO;
import com.farjuce.appreservas.logica.CustomerLogic;

import java.util.List;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private CustomerLogic logic;

    public CustomerController(CustomerLogic logicCustomer) {
        this.logic = logicCustomer;
    }

    @PostMapping(path = "/app/customer/add")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {

        String message = "Customer created";
        logic.addCustomer(customerDTO);

        logger.info("New customer added: {}", customerDTO.getName());

        return message;
    }

    @GetMapping(path = "/app/customer/getAll")
    public List<Customer> getCustomer() {

        List<Customer> customers = logic.getAllCustomers();

        logger.info("Retrieved {} customers", customers.size());

        return customers;
    }

}
