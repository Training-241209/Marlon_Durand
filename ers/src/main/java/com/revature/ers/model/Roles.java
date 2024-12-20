package com.revature.ers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int role_id;
    @Column(name = "role")
    private Integer role;
    private Long users;

    // Constructors
    public Roles(int id, int role, Long users) {
        this.role_id = id;
        this.role = role;
        this.users = users;
    }

    public int getId() {
        return role_id;
    }

    public void setId(int id) {
        this.role_id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Long getUsers() {
        return users;
    }

    public void setUsers(Long users) {
        this.users = users;
    }
    
}
