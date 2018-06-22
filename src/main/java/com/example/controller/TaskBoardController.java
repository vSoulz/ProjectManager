package com.example.controller;


import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<Task> mainPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        List<Task> tasks = new ArrayList<>();
        tasks = user.getTasks();
        tasks.add(new Task("Projekt","Projekt na pp",false));
        tasks.add(new Task("Projekt2","Projekt na hd",false));
        user.setTasks(tasks);
        taskRepository.save(tasks);
        userRepository.save(user);
        return user.getTasks();
    }

}
