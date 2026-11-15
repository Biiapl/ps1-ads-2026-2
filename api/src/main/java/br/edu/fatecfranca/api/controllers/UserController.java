
package br.edu.fatecfranca.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import br.edu.fatecfranca.api.dtos.LoginResponse;
import br.edu.fatecfranca.api.services.TokenService;
import br.edu.fatecfranca.api.dtos.LoginRequest;
import br.edu.fatecfranca.api.dtos.UserRequest;
import br.edu.fatecfranca.api.dtos.UserResponse;
import br.edu.fatecfranca.api.entities.User;
import br.edu.fatecfranca.api.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
private final TokenService tokenService;

public UserController(UserService service, TokenService tokenService) {
    this.service = service;
    this.tokenService = tokenService;
}

 @PostMapping("/login")
public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest request) {

    return service.authenticate(request)
            .map(user -> new LoginResponse(
                    tokenService.generate(user),
                    new UserResponse(user)))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build());
}

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @Valid @RequestBody UserRequest request) {

        User savedUser = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserResponse(savedUser));
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll()
                .stream()
                .map(UserResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(UserResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        User updatedUser = service.update(id, request);

        return ResponseEntity.ok(new UserResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
