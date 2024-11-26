package com.vrvsecurity.rbacdemo.controller;

import com.vrvsecurity.rbacdemo.model.Role;
import com.vrvsecurity.rbacdemo.model.User;
import com.vrvsecurity.rbacdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam Set<String> roles) {
        User user = userService.createUser(username, password, roles);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestParam String name) {
        Role role = userService.createRole(name);
        return ResponseEntity.ok(role);
    }
}
