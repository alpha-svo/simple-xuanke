package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }

    @PostMapping
    public void insertUser(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateById(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.removeById(id);
    }
}
