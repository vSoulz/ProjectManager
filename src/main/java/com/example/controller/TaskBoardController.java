package com.example.controller;


import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@RestController
public class TaskBoardController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/taskboard", method = RequestMethod.GET)
    public List<Task> TaskBoardWeb(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Projekt","Projekt na pp",false, user));
        tasks.add(new Task("Projekt2","Projekt na hd",false, user));
        taskRepository.save(tasks);
        userRepository.save(user);
        return taskRepository.findByUser(userRepository.findByEmail(auth.getName()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String TaskBoardMove(@RequestBody String redirect){
        return "localhost:8080/" + redirect;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void TaskBoardAdd(@RequestBody Task task, String mail){
        task.setUser(userRepository.findByEmail(mail));
        taskRepository.save(task);
    }



}
