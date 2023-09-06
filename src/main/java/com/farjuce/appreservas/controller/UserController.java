package com.farjuce.appreservas.controller;

import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.user.User;
import com.farjuce.appreservas.bd.user.UserRepository;
import com.farjuce.appreservas.logica.UserLogic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserLogic logic;

    public UserController(UserLogic logic){
        this.logic = logic;
    }

    @GetMapping(path = "/users/getAll")
    public List<User> getAllUsers() {
        return logic.getAllUsers();
    }
}
