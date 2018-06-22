package com.example.controller;


import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskBoardController {

    UserRepository userRepository;

    @RequestMapping(value="/mainpage", method = RequestMethod.GET)
    public String mainPage(){

        return "Hello";
    }

}
