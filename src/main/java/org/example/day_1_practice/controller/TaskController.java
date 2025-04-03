package org.example.day_1_practice.controller;

import org.example.day_1_practice.entity.Task;
import org.example.day_1_practice.repository.TaskRepository;
import org.example.day_1_practice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping("/by-title")
    public ResponseEntity<Task> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(taskService.findByTitle(title));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<Task> update(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(task, task.getId()));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> delete(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.deleteTaskById(taskId));
    }

    @PostMapping()
    public ResponseEntity<Task> create(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

}
