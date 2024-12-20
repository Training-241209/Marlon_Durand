package com.revature.ers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;


@Entity
public class Users {

    @Column(name="usersId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;
    private String username;
    private String password;
    private Integer role; // 1 ='Manager' and 2 = 'Default'

    public Users() {

    }

    // For creating users
    public Users (String username, String password) {
        this.username = username;
        this.password = password;
    }

    // For retriving users
    public Users(Long userId, String username, String password, Integer role) {
        this.usersId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getID() {
        return usersId;
    }

    public void setID(Long userId) {
        this.usersId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "usersId=" + usersId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

}
