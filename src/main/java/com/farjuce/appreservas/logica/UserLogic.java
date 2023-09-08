package com.farjuce.appreservas.logica;

import com.farjuce.appreservas.bd.user.User;
import com.farjuce.appreservas.bd.user.UserRepository;
import com.farjuce.appreservas.controller.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogic {

    private UserRepository userRepository;

    public UserLogic(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserDTO userDTO){
        User user = new User();
        user.setUser_id(userDTO.getUser_id());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone_number(userDTO.getPhone_number());
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
