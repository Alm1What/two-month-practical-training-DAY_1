package org.example.day_1_practice.service;

import org.example.day_1_practice.entity.Task;
import org.example.day_1_practice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {


    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty() ||
                task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Task title or description is empty");
        }
        task.setCreateTime(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task updateTask(Task task, Long taskId) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task does not exist"));

        if (task.getTitle() == null || task.getTitle().isEmpty() ||
                task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Task title or description is empty");
        }

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());

        return taskRepository.save(existingTask);
    }


    public Task deleteTaskById(Long taskId) {
        if (!taskRepository.findById(taskId).isPresent()) {
            throw new IllegalArgumentException("Task does not exist");
        }
        Task task = taskRepository.findById(taskId).get();
        taskRepository.delete(task);
        return task;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findByTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Task title is empty");
        }
        return taskRepository.findByTitle(title);
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task does not exist"));
    }






}
