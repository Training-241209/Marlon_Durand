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
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private ReimbursementServices reimbursementService;

    @Autowired
    private UserServices userService;

    @GetMapping(value = "/ping")
    public ResponseEntity<String> ping() {
        System.out.println("Ping pong!");
        return ResponseEntity.ok().body("Pong!");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users users) {
        if (userService.getUsersByUsername(users.getUsername()) == null) {
            Users createdUser = userService.createUser(users);
            return ResponseEntity.ok(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Users> loginUser(@RequestBody Users users) {
        Users tempUser = userService.getUsersByUsername(users.getUsername());
        if (tempUser != null && tempUser.getPassword().equals(users.getPassword()) 
            && tempUser.getUsername().equals(users.getUsername())) {
                return ResponseEntity.ok(userService.getUsersByUsername(users.getUsername()));
            } else {
            return ResponseEntity.status(204).build();
            }
    }

    @GetMapping(value ="/user")
    public ResponseEntity<List<Users>> getAllUsers() {
       List<Users> users = userService.getAllUsers();
       if (users == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return ResponseEntity.ok(users);
    }

    @GetMapping(value ="/user/{usersId}")
    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
        if(userService.getUsersById(userId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Users user = userService.getUsersById(userId);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value ="/user/{usersId}")
    public ResponseEntity<Users> updateUserRole(@PathVariable Long user_id, @PathVariable Integer role) {
        Users exsitingUser = userService.getUsersById(user_id);
        if(exsitingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        exsitingUser.setRole(role);
        userService.changeUsersRole(user_id, role);
        return ResponseEntity.ok(exsitingUser);
    }

    @DeleteMapping(value ="/user/{usersId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long user_id) {
        if(userService.getUsersById(user_id) == null) {
            return ResponseEntity.status(204).build();
        }
        userService.deleteUserById(user_id);
        reimbursementService.deleteReimbursementByUser(user_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value ="/reimbursement")
    public ResponseEntity<Reimbursement> createReimbursement(@RequestBody Reimbursement reimbursement) {
        if(userService.getUsersById(reimbursement.getUser()) != null) {
            Reimbursement newReimbursement = reimbursementService.createReimbersement(reimbursement);
            return ResponseEntity.ok(newReimbursement);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value ="/reimbursment/{usersId}")
    public ResponseEntity<List<Reimbursement>> getReimbursementsByUser(@PathVariable Long userId){
        Users tempUser = userService.getUsersById(userId);
        if(tempUser != null) {
            List<Reimbursement> reimbursements = reimbursementService.getAllReimbursementByUser(userId);
            return ResponseEntity.ok(reimbursements);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value ="/reimbursment/{status}")
    public ResponseEntity<List<Reimbursement>> getReimbursementByStatus(@PathVariable String status) {
        List<Reimbursement> reimbursements = reimbursementService.getReimbursementsByStatus(status);
        return ResponseEntity.ok(reimbursements);
    }

}
