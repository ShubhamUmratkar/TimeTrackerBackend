package com.example.timetracker.controller;

import com.example.timetracker.entity.User;
import com.example.timetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public String login(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return "Login successful as " + user.getProfession();
            }
        }
        return "Invalid email or password";
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
