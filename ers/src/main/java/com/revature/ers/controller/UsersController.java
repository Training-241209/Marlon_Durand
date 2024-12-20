package com.revature.ers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Users tempuser = userService.getUsersByUsername(users.getUsername());
        if (tempuser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            Users createdUser = userService.createUser(users);
            return ResponseEntity.ok(createdUser);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Users> loginUser(@RequestBody Users users) {
        if (users == null || users.getUsername() == null || users.getPassword() == null) {
            return ResponseEntity.badRequest().build(); 
        }
        try{
            Users tempUser = userService.getUsersByUsername(users.getUsername());
            if (tempUser.getPassword().equals(users.getPassword()) 
                && tempUser.getUsername().equals(users.getUsername())) {
                    return ResponseEntity.ok(userService.getUsersByUsername(users.getUsername()));
                } else {
                return ResponseEntity.status(400).build();
                }
        } catch (Exception e) {
            return ResponseEntity.status(501).build();
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

    @PatchMapping(value ="/user/{usersId}/{role}")
    public ResponseEntity<Users> updateUserRole(@PathVariable Long user_id, @PathVariable Integer role) {
        Users exsitingUser = userService.getUsersById(user_id);
        if(exsitingUser == null) {
            return ResponseEntity.status(404).build();
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
            Reimbursement newReimbursement = reimbursementService.createReimbursement(reimbursement);
            return ResponseEntity.ok(newReimbursement);
        } else {
            return ResponseEntity.status(400).body(null);
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

    @PatchMapping(value = ("/reimbursment/aprove/{usersId}/{status}"))
    public ResponseEntity<List<Reimbursement>> aproveReimbursement(@PathVariable Long usersId, @PathVariable String status) {
         reimbursementService.updateReimbursementStatus(usersId,status);
         return ResponseEntity.ok(reimbursementService.getAllReimbursementByUser(usersId));
    }

    @GetMapping(value ="/reimbursment/{status}")
    public ResponseEntity<List<Reimbursement>> getReimbursementByStatus(@PathVariable String status) {
        List<Reimbursement> reimbursements = reimbursementService.getReimbursementsByStatus(status);
        return ResponseEntity.ok(reimbursements);
    }

}
