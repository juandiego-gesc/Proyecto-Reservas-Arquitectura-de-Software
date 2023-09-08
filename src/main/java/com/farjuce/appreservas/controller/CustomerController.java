package com.farjuce.appreservas.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.farjuce.appreservas.controller.dto.CustomerDTO;
import com.farjuce.appreservas.logica.CustomerLogic;

@RestController
public class CustomerController {

    private CustomerLogic logicCustomer;

    public CustomerController(CustomerLogic logicCustomer) {
        this.logicCustomer = logicCustomer;
    }


    @PostMapping(path = "/Customer/create")
    public void createCustomer(@RequestBody CustomerDTO CustomerDTO) {
        logicCustomer.createCustomer(CustomerDTO);
    }
}
