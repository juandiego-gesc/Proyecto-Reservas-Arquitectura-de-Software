package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.brach.Branch;
import com.farjuce.appreservas.bd.brach.BranchRepository;
import com.farjuce.appreservas.controller.dto.BranchDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;

@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BranchLogicTest {

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    BranchLogic branchLogic;

    @Test
    void Given_branch_When_added_Then_save() {
        BranchDTO branchDTO = new BranchDTO("Branch Test Name", "Cll 1 #1-1a", "Branch test type", LocalTime.of(10, 00, 00), LocalTime.of(11, 00, 00));

        branchLogic.addBranch(branchDTO);

        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        branch.setType(branchDTO.getType());
        branch.setOpeningTime(branchDTO.getOpeningTime());
        branch.setClosureTime(branchDTO.getClosureTime());

        Mockito.verify(branchRepository).save(branch);
    }
}