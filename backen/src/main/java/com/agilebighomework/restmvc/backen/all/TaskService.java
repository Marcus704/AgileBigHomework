package com.agilebighomework.restmvc.backen.all;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
@Service
public class TaskService {
    private HashMap<String, Task> db;
    public TaskService(){
        db = new HashMap<String, Task>();
        db.put("1",new Task("1","test",new Date()));
        db.put("2", new Task("2","find",new Date()));
    }

    public boolean add(Task t){
        if(db.containsKey(t.getId()))
            return false;
        db.put(t.getId(),t);
        return true;
    }

    public HashMap<String, Task> findAll(){
        if(db.isEmpty())
            return null;
        return db;
    }

    public boolean delete(String id){
        if(db.isEmpty())
            return false;
        db.remove(id);
        return true;
    }

    public Task findById(String id){
        return db.get(id);
    }

    public boolean changeById(String id,Task newTask){
        if(db.containsKey(id)){
            db.remove(id);
            db.put(newTask.getId(),newTask);
            return true;
        }
        return false;
    }

    public HashMap<String, Task> getDb() {
        return db;
    }
}
