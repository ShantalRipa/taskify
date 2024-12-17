package com.valerie.taskify.service;

import com.valerie.taskify.entity.Task;
import com.valerie.taskify.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    // Mock the TaskRepository dependency
    @Mock
    private TaskRepository taskRepository;

    // Inject the mocks into TaskService
    @InjectMocks
    private TaskService taskService;

    // Setup method that runs before each test to initialize the TaskService
    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    // Test case for creating a task
    @Test
    void createTask_shouldAddTaskToList() {
        // Create a new Task with title and description
        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("Description of the new task");

        // Mock the save method of TaskRepository
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Create the task using the taskService
        Task createdTask = taskService.createTask(task);

        // Verify the task is created with a non-null ID and correct title and description
        assertNotNull(createdTask.getId(), "Task ID should not be null");
        assertEquals("New Task", createdTask.getTitle());
        assertEquals("Description of the new task", createdTask.getDescription());

        // Verify that save was called once
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    // Test case for retrieving a task by its ID
    @Test
    void getTaskById_shouldReturnTaskIfExists() {
        // Create a task and mock the repository
        Task task = new Task();
        task.setTitle("Sample Task");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Retrieve the task by ID (assuming the ID is 1)
        Optional<Task> foundTask = taskService.getTaskById(1L);

        // Verify that the task is found and matches the expected title
        assertTrue(foundTask.isPresent(), "Task should be found");
        assertEquals("Sample Task", foundTask.get().getTitle());
    }

    // Test case for updating an existing task
    @Test
    void updateTask_shouldModifyExistingTask() {
        // Create a task and mock the repository
        Task task = new Task();
        task.setTitle("Old Title");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Create a task with the new title for updating
        Task updatedTask = new Task();
        updatedTask.setTitle("New Title");

        // Update the task with the new title
        Optional<Task> updated = taskService.updateTask(1L, updatedTask);

        // Verify the task is updated and the title is changed
        assertTrue(updated.isPresent(), "Task should be updated");
        assertEquals("New Title", updated.get().getTitle());

        // Verify that save was called once
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    // Test case for deleting a task
    @Test
    void deleteTask_shouldRemoveTaskFromList() {
        // Create a task and mock the repository
        Task task = new Task();
        task.setTitle("Task to delete");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(any(Task.class));

        // Delete the task by ID
        boolean deleted = taskService.deleteTask(1L);

        // Verify that the task is deleted and the task list is empty
        assertTrue(deleted, "Task should be deleted");

        // Verify that delete was called once
        verify(taskRepository, times(1)).delete(any(Task.class));
    }
}
