package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.brach.Branch;
import com.farjuce.appreservas.bd.brach.BranchRepository;
import com.farjuce.appreservas.controller.dto.BranchDTO;
import org.springframework.stereotype.Service;

import java.util.List;

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
        branch.setOpeningTime(branchDTO.getOpeningTime());
        branch.setClosureTime(branchDTO.getClosureTime());
        return repository.save(branch);
    }

    public List<Branch> getAllBranches() {
        return repository.findAll();
    }
}
