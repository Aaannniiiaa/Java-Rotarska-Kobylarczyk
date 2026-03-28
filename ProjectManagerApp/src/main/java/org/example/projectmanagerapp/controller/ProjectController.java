package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Projects", description = "Operacje na projektach")
public class ProjectController {

    private final ProjectRepository projectRepository;

    @GetMapping
    @Operation(
            summary = "Pobierz wszystkie projekty",
            description = "Zwraca listę wszystkich projektów zapisanych w bazie danych"
    )
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
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
        return projectRepository.save(project);
    }
}