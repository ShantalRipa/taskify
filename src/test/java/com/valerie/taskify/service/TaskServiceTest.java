package com.valerie.taskify.service;

import com.valerie.taskify.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void createTask_shouldAddTaskToList() {
        // Arrange
        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("Description of the new task");

        // Act
        Task createdTask = taskService.createTask(task);

        // Assert
        assertNotNull(createdTask.getId(), "Task ID should not be null");
        assertEquals("New Task", createdTask.getTitle());
        assertEquals("Description of the new task", createdTask.getDescription());
        assertEquals(1, taskService.getAllTasks().size(), "There should be one task in the list");
    }

    @Test
    void getTaskById_shouldReturnTaskIfExists() {
        // Arrange
        Task task = new Task();
        task.setTitle("Sample Task");
        taskService.createTask(task);

        // Act
        Optional<Task> foundTask = taskService.getTaskById(1L);

        // Assert
        assertTrue(foundTask.isPresent(), "Task should be found");
        assertEquals("Sample Task", foundTask.get().getTitle());
    }

    @Test
    void updateTask_shouldModifyExistingTask() {
        // Arrange
        Task task = new Task();
        task.setTitle("Old Title");
        taskService.createTask(task);

        Task updatedTask = new Task();
        updatedTask.setTitle("New Title");

        // Act
        Optional<Task> updated = taskService.updateTask(1L, updatedTask);

        // Assert
        assertTrue(updated.isPresent(), "Task should be updated");
        assertEquals("New Title", updated.get().getTitle());
    }

    @Test
    void deleteTask_shouldRemoveTaskFromList() {
        // Arrange
        Task task = new Task();
        task.setTitle("Task to delete");
        taskService.createTask(task);

        // Act
        boolean deleted = taskService.deleteTask(1L);

        // Assert
        assertTrue(deleted, "Task should be deleted");
        assertTrue(taskService.getAllTasks().isEmpty(), "Task list should be empty");
    }
}

