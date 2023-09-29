package com.farjuce.appreservas.integration;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.controller.dto.CustomerDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.List;


@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void Given_customer_When_added_Then_created() {

        List<String> customerName = null;
        CustomerDTO customerDTO = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);

        restTemplate.postForEntity("/customer/add",customerDTO, String.class);

        ResponseEntity<List> customersDB = restTemplate.getForEntity("/customer/getAll", List.class);

        Assertions.assertFalse(customersDB.getBody().isEmpty());
    }

    @Test
    public void Given_customers_When_get_all_customers_Then_return_all_customers(){


        CustomerDTO customerDTO1 = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);
        CustomerDTO customerDTO2 = new CustomerDTO("Pedro", "pedro@unisabana.edu.co", 4L);

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();

        customer1.setName(customerDTO1.getName());
        customer1.setEmail(customerDTO1.getEmail());
        customer1.setPhone_number(customerDTO1.getPhone_number());

        customer2.setName(customerDTO2.getName());
        customer2.setEmail(customerDTO2.getEmail());
        customer2.setPhone_number(customerDTO2.getPhone_number());


        restTemplate.<String>postForEntity("/customer/add", customer1, String.class);
        restTemplate.<String>postForEntity("/customer/add", customer2, String.class);

        ResponseEntity<List> customersBD = restTemplate.getForEntity("/customer/getAll", List.class);

        Assertions.assertEquals(2, customersBD.getBody().size());

    }
}
