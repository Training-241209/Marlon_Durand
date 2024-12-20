package com.revature.ers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ers.model.Reimbursement;
import com.revature.ers.model.Users;
import com.revature.ers.services.ReimbursementServices;
import com.revature.ers.services.UserServices;

@RestController
@RequestMapping(value = "/auth")
public class auth {
    
    @Autowired
    private ReimbursementServices reimbursementService;

    @Autowired
    private UserServices userService;


    @GetMapping(value="/ping")
    public ResponseEntity<String> ping() {
        System.out.println("Ping pong!");
        return ResponseEntity.ok().body("Pong!");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users users) {
        if (userService.getUsersByUsername(users.getUsername()) != null) {
            Users createdUser = userService.createUser(users);
            return ResponseEntity.ok(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping(value ="/login")
    public ResponseEntity<Users> loginUser(@PathVariable String username, @PathVariable String password) {
        Users tempUser = userService.getUsersByUsername(username);
        if (tempUser != null && tempUser.getPassword().equals(password) 
            && tempUser.getUsername().equals(username)) {
                return ResponseEntity.ok(userService.getUsersByUsername(username));
            } else {
            return ResponseEntity.status(204).build();
            }
    }
}
