package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.projectmanagerapp.entity.Users;
import org.example.projectmanagerapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Operacje na użytkownikach")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(
            summary = "Pobierz wszystkich użytkowników",
            description = "Zwraca listę wszystkich użytkowników zapisanych w bazie danych"
    )
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Operation(
            summary = "Dodaj nowego użytkownika",
            description = "Tworzy nowego użytkownika i zapisuje go w bazie danych"
    )
    public Users createUser(
            @Parameter(description = "Obiekt użytkownika przekazywany w żądaniu")
            @RequestBody Users user
    ) {
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Zaktualizuj użytkownika",
            description = "Aktualizuje dane istniejącego użytkownika na podstawie jego ID"
    )
    public Users updateUser(
            @Parameter(description = "ID użytkownika, którego dane mają zostać zaktualizowane")
            @PathVariable Long id,
            @Parameter(description = "Nowe dane użytkownika")
            @RequestBody Users user
    ) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Usuń użytkownika",
            description = "Usuwa użytkownika z bazy danych na podstawie jego ID"
    )
    public void deleteUser(
            @Parameter(description = "ID użytkownika, który ma zostać usunięty")
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
    }
}