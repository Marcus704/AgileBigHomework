package com.agilebighomework.restmvc.backen.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/api/tasks")
    public List<Task> getAll(){
        HashMap<String, Task> all = taskService.findAll();
        List<Task> tasks = new LinkedList<>();
        for(Map.Entry<String, Task> t : all.entrySet()) {
            tasks.add(t.getValue());
        }
        return  tasks;
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
