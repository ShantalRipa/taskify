package com.valerie.taskify.service;

import com.valerie.taskify.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    // Setup method that runs before each test to initialize the TaskService
    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    // Test case for creating a task
    @Test
    void createTask_shouldAddTaskToList() {
        // Create a new Task with title and description
        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("Description of the new task");

        // Create the task using the taskService
        Task createdTask = taskService.createTask(task);

        // Verify the task is created with a non-null ID and correct title and description
        assertNotNull(createdTask.getId(), "Task ID should not be null");
        assertEquals("New Task", createdTask.getTitle());
        assertEquals("Description of the new task", createdTask.getDescription());
        // Ensure the task has been added to the task list
        assertEquals(1, taskService.getAllTasks().size(), "There should be one task in the list");
    }

    // Test case for retrieving a task by its ID
    @Test
    void getTaskById_shouldReturnTaskIfExists() {
        // Create a task and add it to the service
        Task task = new Task();
        task.setTitle("Sample Task");
        taskService.createTask(task);

        // Retrieve the task by ID (assuming the ID is 1)
        Optional<Task> foundTask = taskService.getTaskById(1L);

        // Verify that the task is found and matches the expected title
        assertTrue(foundTask.isPresent(), "Task should be found");
        assertEquals("Sample Task", foundTask.get().getTitle());
    }

    // Test case for updating an existing task
    @Test
    void updateTask_shouldModifyExistingTask() {
        // Create a task and add it to the service
        Task task = new Task();
        task.setTitle("Old Title");
        taskService.createTask(task);

        // Create a task with the new title for updating
        Task updatedTask = new Task();
        updatedTask.setTitle("New Title");

        // Update the task with the new title
        Optional<Task> updated = taskService.updateTask(1L, updatedTask);

        // Verify the task is updated and the title is changed
        assertTrue(updated.isPresent(), "Task should be updated");
        assertEquals("New Title", updated.get().getTitle());
    }

    // Test case for deleting a task
    @Test
    void deleteTask_shouldRemoveTaskFromList() {
        // Create a task and add it to the service
        Task task = new Task();
        task.setTitle("Task to delete");
        taskService.createTask(task);

        // Delete the task by ID
        boolean deleted = taskService.deleteTask(1L);

        // Verify that the task is deleted and the task list is empty
        assertTrue(deleted, "Task should be deleted");
        assertTrue(taskService.getAllTasks().isEmpty(), "Task list should be empty");
    }
}
