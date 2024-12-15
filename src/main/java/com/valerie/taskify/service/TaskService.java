package com.valerie.taskify.service;

import com.valerie.taskify.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private Long currentId = 1L;

    // Create a new task
    public Task createTask(Task task) {
        task.setId(currentId++);
        tasks.add(task);
        return task;
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return tasks;
    }

    // Get task by ID
    public Optional<Task> getTaskById(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    // Update a task
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return getTaskById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            return task;
        });
    }

    // Delete a task
    public boolean deleteTask(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}

