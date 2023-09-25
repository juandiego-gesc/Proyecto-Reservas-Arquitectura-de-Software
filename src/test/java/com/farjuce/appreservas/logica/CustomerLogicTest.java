package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.controller.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
class CustomerLogicTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerLogic customerLogic;

    @Test
    void Given_customer_When_added_Then_save() {
        CustomerDTO customerDTO = new CustomerDTO(
                "Juan",
                "juangares@unisabana.edu.co",
                3L);

        Customer customer = customerLogic.addCustomer(customerDTO);

        assertEquals("Juan", customer.getName());
        assertEquals("juangares@unisabana.edu.co", customer.getEmail());
        assertEquals(3l, customer.getPhone_number());

    }

    @Test
    void getAllCustomers() {
    }
}