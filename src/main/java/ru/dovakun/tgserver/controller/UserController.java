package ru.dovakun.tgserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dovakun.tgserver.entity.User;
import ru.dovakun.tgserver.repo.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Пользователь с таким именем уже существует");
        }
        userRepository.save(user);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }
    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existingUser.isPresent()) {
            return ResponseEntity.ok(existingUser.get().getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

