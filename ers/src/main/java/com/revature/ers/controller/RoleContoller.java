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
@RequestMapping(value = "/role")
public class RoleContoller {
    @Autowired
    private ReimbursementServices reimbursementService;

    @Autowired
    private UserServices userService;

    @GetMapping(value="/ping")
    public ResponseEntity<String> ping() {
        System.out.println("Ping pong!");
        return ResponseEntity.ok().body("Pong!");
    }

    
}
