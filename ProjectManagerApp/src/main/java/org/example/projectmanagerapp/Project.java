package org.example.projectmanagerapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Project {

    @Id
    private Long id;

    private String name;
    private String description;
}