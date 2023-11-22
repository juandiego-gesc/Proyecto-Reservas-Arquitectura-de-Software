package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.controller.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.verify;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerLogicTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerLogic customerLogic;

    @Test
    void Given_customer_When_added_Then_save() {
        CustomerDTO customerDTO = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);

        customerLogic.addCustomer(customerDTO);

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        Mockito.verify(customerRepository).save(customer);
    }

    @Test
    void Given_customers_When_get_all_customers_Then_return_all_customers() {

        customerLogic.getAllCustomers();
        verify(customerRepository).findAll();
    }
}