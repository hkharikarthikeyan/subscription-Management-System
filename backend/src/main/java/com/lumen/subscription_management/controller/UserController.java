package com.lumen.subscription_management.controller;

import com.lumen.subscription_management.model.UserDetails;
import com.lumen.subscription_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public UserDetails createUser(@RequestBody UserDetails user) {
        return userRepo.save(user);
    }

    @GetMapping("/{id}")
    public UserDetails getUser(@PathVariable Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
