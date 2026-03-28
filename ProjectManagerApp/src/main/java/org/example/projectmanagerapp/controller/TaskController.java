package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.projectmanagerapp.entity.Task;
import org.example.projectmanagerapp.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Operacje na zadaniach")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    @Operation(
            summary = "Pobierz wszystkie zadania",
            description = "Zwraca listę wszystkich zadań zapisanych w bazie danych"
    )
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    @Operation(
            summary = "Dodaj nowe zadanie",
            description = "Tworzy nowe zadanie i zapisuje je w bazie danych"
    )
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
}