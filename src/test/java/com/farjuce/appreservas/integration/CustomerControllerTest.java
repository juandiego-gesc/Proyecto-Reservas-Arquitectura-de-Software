package com.farjuce.appreservas.integration;

import com.farjuce.appreservas.controller.dto.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Given_customer_When_added_Then_created() {
        CustomerDTO customerDTO = new CustomerDTO("Juan", "juangares@unisabana.edu.co", 3L);
        restTemplate.postForEntity("/app/customer/add", customerDTO, String.class);
        ResponseEntity<List> customersDB = restTemplate.getForEntity("/app/customer/getAll", List.class);
        Assertions.assertFalse(customersDB.getBody().isEmpty());
    }
}

