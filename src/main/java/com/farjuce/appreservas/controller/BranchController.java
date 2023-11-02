package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.controller.dto.BranchDTO;
import com.farjuce.appreservas.logica.BranchLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
