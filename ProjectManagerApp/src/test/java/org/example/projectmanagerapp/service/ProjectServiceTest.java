package org.example.projectmanagerapp.service;

import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.entity.Users;
import org.example.projectmanagerapp.repository.ProjectRepository;
import org.example.projectmanagerapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        projectRepository = mock(ProjectRepository.class);
        userRepository = mock(UserRepository.class);
        projectService = new ProjectService(projectRepository, userRepository);
    }

    @Test
    void shouldCreateProject() {
        Project project = new Project();
        project.setName("Test Project");

        when(projectRepository.save(project)).thenReturn(project);

        Project result = projectService.createProject(project);

        assertEquals("Test Project", result.getName());
        verify(projectRepository).save(project);
    }

    @Test
    void shouldAddUserToProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Projekt Jira");

        Users user = new Users();
        user.setId(1L);
        user.setUsername("anna");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(projectRepository.save(project)).thenReturn(project);

        Project result = projectService.addUserToProject(1L, 1L);

        assertEquals(1, result.getUsers().size());
        assertEquals("anna", result.getUsers().iterator().next().getUsername());

        verify(projectRepository).findById(1L);
        verify(userRepository).findById(1L);
        verify(projectRepository).save(project);
    }
}