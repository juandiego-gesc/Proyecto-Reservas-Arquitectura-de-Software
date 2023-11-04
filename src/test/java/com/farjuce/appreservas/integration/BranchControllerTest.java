package com.farjuce.appreservas.integration;

import com.farjuce.appreservas.controller.dto.BranchDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.List;


@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class BranchControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void Given_branch_When_added_Then_create_branch() {
        BranchDTO branchDTO = new BranchDTO("Juan", "Bogotá", "Sede", LocalTime.of(12,30), LocalTime.of(2,30));

        ResponseEntity<Boolean> statusBranchAdd = restTemplate.postForEntity("/app/branch/add", branchDTO, Boolean.class);

        Assertions.assertTrue(statusBranchAdd.getBody());

    }

    @Test
    void Given_no_branches_When_getAllBranches_Then_return_empty_list() {

        ResponseEntity<List> branches = restTemplate.getForEntity("/app/branch/getAll", List.class);

        Assertions.assertFalse(branches.getBody().isEmpty());
    }



}
