package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.task.Task;
import com.farjuce.appreservas.controller.dto.BusinessDTO;
import com.farjuce.appreservas.controller.dto.TaskDTO;
import com.farjuce.appreservas.logica.BusinessLogic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusinessController {

    private BusinessLogic logic;

    public BusinessController(BusinessLogic logic) {
        this.logic = logic;
    }

    @PostMapping(path = "/business/add")
    public boolean createBusiness(@RequestBody BusinessDTO businessDTO){
        logic.addBusiness(businessDTO);
        return true;
    }

}
