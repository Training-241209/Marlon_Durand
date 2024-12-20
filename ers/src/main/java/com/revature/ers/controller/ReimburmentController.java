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
@RequestMapping(value = "/ticket")
public class ReimburmentController {
    
    @Autowired
    private ReimbursementServices reimbursementService;

    @Autowired
    private UserServices userService;

    @GetMapping(value="/ping")
    public ResponseEntity<String> ping() {
        System.out.println("Ping pong!");
        return ResponseEntity.ok().body("Pong!");
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

    @GetMapping(value ="/user/{userId}/reimbursment")
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
