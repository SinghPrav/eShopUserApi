package com.eshop.user.UserService.controller;

import com.eshop.user.UserService.exception.ResourceNotFoundException;
import com.eshop.user.UserService.model.User;
import com.eshop.user.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eshop")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User User) {
        return userRepository.save(User);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findUserId(@PathVariable(value = "id")
                                                Integer id) {
        User User = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found" + id));
        return ResponseEntity.ok().body(User);

    }
}
