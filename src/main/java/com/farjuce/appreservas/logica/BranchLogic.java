package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.brach.Branch;
import com.farjuce.appreservas.bd.brach.BranchRepository;
import com.farjuce.appreservas.controller.dto.BranchDTO;
import org.springframework.stereotype.Service;

@Service
public class BranchLogic {

    private BranchRepository repository;

    public BranchLogic(BranchRepository repository) {
        this.repository = repository;
    }

    public Branch addBranch(BranchDTO branchDTO) {

        Branch branch = new Branch();

        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        branch.setType(branchDTO.getType());
        branch.setOpening_time(branchDTO.getOpening_time());
        branch.setClosure_time(branchDTO.getClosure_time());


        return repository.save(branch);
    }

}
