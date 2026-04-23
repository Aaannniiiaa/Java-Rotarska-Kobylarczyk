package org.example.projectmanagerapp.service;

import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.entity.Task;
import org.example.projectmanagerapp.entity.TaskType;
import org.example.projectmanagerapp.entity.Users;
import org.example.projectmanagerapp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    @DisplayName("Should return all tasks")
    void testGetAllTasks() {
        Task task1 = new Task();
        task1.setTitle("Task1");

        Task task2 = new Task();
        task2.setTitle("Task2");

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return task by id")
    void testGetTaskById() {
        Task task = new Task();
        task.setTitle("TestTask");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertEquals("TestTask", result.getTitle());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should create task")
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("NewTask");

        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertEquals("NewTask", result.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    @DisplayName("Should update task")
    void testUpdateTask() {
        Task existingTask = new Task();
        existingTask.setTitle("OldTask");
        existingTask.setDescription("OldDescription");

        Project project = new Project();
        project.setName("Project1");

        Users user = new Users();
        user.setUsername("User1");

        Task updatedTask = new Task();
        updatedTask.setTitle("NewTask");
        updatedTask.setDescription("NewDescription");
        updatedTask.setTaskType(TaskType.TODO);
        updatedTask.setProject(project);
        updatedTask.setUser(user);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(existingTask);

        Task result = taskService.updateTask(1L, updatedTask);

        assertEquals("NewTask", result.getTitle());
        assertEquals("NewDescription", result.getDescription());
        assertEquals(TaskType.TODO, result.getTaskType());
        assertEquals(project, result.getProject());
        assertEquals(user, result.getUser());

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(existingTask);
    }

    @Test
    @DisplayName("Should delete task")
    void testDeleteTask() {
        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }
}