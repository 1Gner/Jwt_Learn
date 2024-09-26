package com.example.demo;

import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.user;
import com.example.demo.repository.userRepository;


@Component
public class start implements CommandLineRunner {

    @Autowired
    private userRepository repository;


    @Autowired
    private userService service;


    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        user User = new user();

        /*User.setName("bia");
        User.setUsername("bia");
        User.setPassword("123");
        User.getRoles().add("USER");
        User.getRoles().add("MANAGER");

        service.salvarUser(User);*/

    }

}
