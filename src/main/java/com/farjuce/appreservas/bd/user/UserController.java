package com.farjuce.appreservas.bd.user;

import com.farjuce.appreservas.bd.employee.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/user")
    public List<User> getUser() {
        return userRepository.findAll();

    }
    @PostMapping(path = "/user/add")
    public boolean addUser(@RequestBody User user){
        this.userRepository.save(user);
        return true;
    }


}
