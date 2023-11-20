package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.brach.Branch;
import com.farjuce.appreservas.controller.dto.BranchDTO;
import com.farjuce.appreservas.logica.BranchLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BranchController {

    private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

    private BranchLogic logic;

    public BranchController(BranchLogic logic) {
        this.logic = logic;
    }

    @PostMapping(path = "/app/branch/add")
    public boolean createBranch(@RequestBody BranchDTO branchDTO) {
        logger.info("Creating branch: {}", branchDTO);
        logic.addBranch(branchDTO);
        logger.info("Branch created successfully");
        return true;
    }

    @GetMapping(path = "/app/branch/getAll")
    public List<Branch> getBranch() {

        List<Branch> branches = logic.getAllBranches();

        logger.info("Retrieved {} branches", branches.size());

        return branches;
    }

}
