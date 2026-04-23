package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.projectmanagerapp.entity.Task;
import org.example.projectmanagerapp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Operacje na zadaniach")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(
            summary = "Pobierz wszystkie zadania",
            description = "Zwraca listę wszystkich zadań zapisanych w bazie danych"
    )
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    @Operation(
            summary = "Dodaj nowe zadanie",
            description = "Tworzy nowe zadanie i zapisuje je w bazie danych"
    )
    public Task createTask(
            @Parameter(description = "Obiekt zadania przekazywany w żądaniu")
            @RequestBody Task task
    ) {
        return taskService.createTask(task);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Zaktualizuj zadanie",
            description = "Aktualizuje dane istniejącego zadania na podstawie jego ID"
    )
    public Task updateTask(
            @Parameter(description = "ID zadania, które ma zostać zaktualizowane")
            @PathVariable Long id,
            @Parameter(description = "Nowe dane zadania")
            @RequestBody Task task
    ) {
        return taskService.updateTask(id, task);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Usuń zadanie",
            description = "Usuwa zadanie z bazy danych na podstawie jego ID"
    )
    public void deleteTask(
            @Parameter(description = "ID zadania, które ma zostać usunięte")
            @PathVariable Long id
    ) {
        taskService.deleteTask(id);
    }}