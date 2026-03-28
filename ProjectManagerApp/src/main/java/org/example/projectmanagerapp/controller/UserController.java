package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.projectmanagerapp.entity.Users;
import org.example.projectmanagerapp.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Operacje na użytkownikach")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @Operation(
            summary = "Pobierz wszystkich użytkowników",
            description = "Zwraca listę wszystkich użytkowników zapisanych w bazie danych"
    )
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    @Operation(
            summary = "Dodaj nowego użytkownika",
            description = "Tworzy nowego użytkownika i zapisuje go w bazie danych"
    )
    public Users createUser(@RequestBody Users user) {
        return userRepository.save(user);
    }
}