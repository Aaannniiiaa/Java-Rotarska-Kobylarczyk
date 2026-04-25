package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Projects", description = "Operacje na projektach")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @Operation(
            summary = "Pobierz wszystkie projekty",
            description = "Zwraca listę wszystkich projektów zapisanych w bazie danych"
    )
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Pobierz projekt po ID",
            description = "Zwraca jeden projekt na podstawie jego ID"
    )
    public Project getProjectById(
            @Parameter(description = "ID projektu")
            @PathVariable Long id
    ) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    @Operation(
            summary = "Dodaj nowy projekt",
            description = "Tworzy nowy projekt i zapisuje go w bazie danych"
    )
    public Project createProject(
            @Parameter(description = "Obiekt projektu przekazywany w żądaniu")
            @RequestBody Project project
    ) {
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Zaktualizuj projekt",
            description = "Aktualizuje dane istniejącego projektu na podstawie jego ID"
    )
    public Project updateProject(
            @Parameter(description = "ID projektu, który ma zostać zaktualizowany")
            @PathVariable Long id,

            @Parameter(description = "Nowe dane projektu")
            @RequestBody Project project
    ) {
        return projectService.updateProject(id, project);
    }

    @PostMapping("/{projectId}/users/{userId}")
    @Operation(
            summary = "Przypisz użytkownika do projektu",
            description = "Dodaje użytkownika do listy członków projektu"
    )
    public Project addUserToProject(
            @Parameter(description = "ID projektu")
            @PathVariable Long projectId,

            @Parameter(description = "ID użytkownika")
            @PathVariable Long userId
    ) {
        return projectService.addUserToProject(projectId, userId);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Usuń projekt",
            description = "Usuwa projekt z bazy danych na podstawie jego ID"
    )
    public void deleteProject(
            @Parameter(description = "ID projektu, który ma zostać usunięty")
            @PathVariable Long id
    ) {
        projectService.deleteProject(id);
    }
}