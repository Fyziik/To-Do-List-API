package com.example.demo.controllers;

import com.example.demo.models.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //GET ALL
    @CrossOrigin(origins = "*")
    @GetMapping("/readAll")
    public Iterable<Task> readAll() {
        return taskRepository.findAll();
    }

    //POST
    @CrossOrigin(origins = "*")
    @PostMapping("/addTask")
    public ResponseEntity<String> addTask(@ModelAttribute Task task) {
        Task tmp = taskRepository.save(task);
        return ResponseEntity.status(201).header("Location", "/addTask" + tmp.getId()).body("{'msg' : 'Task added'}");
    }

    //DELETE
    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.status(200).body("{'msg' : 'Task deleted'}");
    }



}
