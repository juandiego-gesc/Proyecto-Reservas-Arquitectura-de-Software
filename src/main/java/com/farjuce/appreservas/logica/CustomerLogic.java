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

        Customer customer = Customer.builder()
                .customer_id(customerDTO.getCustomer_id())
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .phone_number(customerDTO.getPhone_number())
                .build();
        customerRepository.save(customer);
    }
}
