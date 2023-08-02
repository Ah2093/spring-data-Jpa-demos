package com.example.springdatajpademo.controllers;

import com.example.springdatajpademo.repositroy.UserRepos;
import com.example.springdatajpademo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PutMapping("/role/{roleName}")
    public void addRoleForAllUsers(@PathVariable String roleName)
    {
            userService.addRoleForAllUsers("user");
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers()
    {
        return ResponseEntity.ok(userService.findAll());
    }

}
