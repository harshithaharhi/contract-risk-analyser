package com.internship.tool.controller;

import com.internship.tool.dto.AuthRequest;
import com.internship.tool.dto.AuthResponse;
import com.internship.tool.entity.User;
import com.internship.tool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        User user = userRepository.findByUsername(request.getUsername()).orElse(null);

        if (user == null) {
              return "User not found";
        }

        if (!user.getPassword().equals(request.getPassword())) {
              return "Invalid password";
        }

     return "Login successful";
}

    @PostMapping("/refresh")
public String refreshToken() {

    return "Token refreshed successfully";
}
}