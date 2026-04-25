package org.example.projectmanagerapp.service;

import jakarta.transaction.Transactional;
import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.entity.Users;
import org.example.projectmanagerapp.repository.ProjectRepository;
import org.example.projectmanagerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existingProject.setName(updatedProject.getName());

        return projectRepository.save(existingProject);
    }

    @Transactional
    public Project addUserToProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        project.getUsers().add(user);

        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}