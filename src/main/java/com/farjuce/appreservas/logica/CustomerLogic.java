package com.farjuce.appreservas.logica;

import org.springframework.stereotype.Service;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.controller.dto.CustomerDTO;

@Service
public class CustomerLogic {

    private CustomerRepository customerRepository;

    public CustomerLogic(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(CustomerDTO customerDTO) {

        Customer customer = new Customer();

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone_number(customerDTO.getPhone_number());

        customerRepository.save(customer);
    }
}
