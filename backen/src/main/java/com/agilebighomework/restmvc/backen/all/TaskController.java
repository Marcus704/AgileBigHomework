package com.agilebighomework.restmvc.backen.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/api/task/{id}")
    public String deleByID(@PathVariable String id){
        if(taskService.delete(id))
            return "success";
        return "fail";
    }

    @GetMapping("/api/task/{id}")
    public Task findById(@PathVariable String id) {
        return  taskService.findById(id);
    }

    @PutMapping("/api/task/{id}")
    public  String changeById(@PathVariable String id,@RequestBody Task t){
        if(taskService.changeById(id,t))
            return "success";
        return "fail";
    }
}
