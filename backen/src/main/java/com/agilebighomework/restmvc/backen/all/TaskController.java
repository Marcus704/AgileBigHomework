package com.agilebighomework.restmvc.backen.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping("/api/task")
    public String addTask(@RequestBody Task t){
        if(taskService.add(t))
            return "success";
        return "failed";
    }

    @GetMapping("/api/tasks/")
    public HashMap<String, Task> getAll(){
        return taskService.findAll();
    }
}
