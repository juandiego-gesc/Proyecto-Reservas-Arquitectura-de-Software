package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.controller.dto.BranchDTO;
import com.farjuce.appreservas.logica.BranchLogic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {

    private BranchLogic logic;

    public BranchController(BranchLogic logic) {
        this.logic = logic;
    }

    @PostMapping(path = "/branch/add")
    public boolean createBranch(@RequestBody BranchDTO branchDTO){
        logic.addBranch(branchDTO);
        return true;
    }

}
