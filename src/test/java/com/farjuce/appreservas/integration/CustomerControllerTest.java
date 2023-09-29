package com.farjuce.appreservas.integration;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.customer.CustomerRepository;
import com.farjuce.appreservas.controller.dto.CustomerDTO;
import com.sun.xml.bind.v2.TODO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

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

        CustomerDTO customerDTO = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);

        ResponseEntity<String> message = restTemplate.<String>postForEntity("/customer/add", customerDTO, String.class);

        Assertions.assertEquals("Customer created", message.getBody());
    }

    @Test
    public void Given_customers_When_get_all_customers_Then_return_all_customers(){


        /*CustomerDTO customerDTO1 = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);
        CustomerDTO customerDTO2 = new CustomerDTO("Pedro", "pedro@unisabana.edu.co", 4L);

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();

        customer1.setName(customerDTO1.getName());
        customer1.setEmail(customerDTO1.getEmail());
        customer1.setPhone_number(customerDTO1.getPhone_number());

        customer2.setName(customerDTO2.getName());
        customer2.setEmail(customerDTO2.getEmail());
        customer2.setPhone_number(customerDTO2.getPhone_number());

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        // When
        ResponseEntity<Customer> response = restTemplate.getForEntity("/customer/getAll", Customer.class);

        // Then
        Customer customer = response.getBody();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        Assertions.assertEquals(customer1.getName(), customers.get(0).getName());
        Assertions.assertEquals(customer2.getName(), customers.get(1).getName());

*/

    }
}
