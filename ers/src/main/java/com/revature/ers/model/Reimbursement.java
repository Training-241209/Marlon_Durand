package com.revature.ers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;
    private String description;
    private String status = "Pending"; // pending by default
    private Long users; 

    // Constructors
    public Reimbursement(Long id, Float amount, String description, String status, Long users) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.users = users;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public Float getAmount(){
        return amount;
    }

    public void setAmount(Float amount){
        this.amount = amount;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Long getUser(){
        return users;
    }

    public void setUser(Long users){
        this.users = users;
    }

    @Override
    public String toString() {
        return "Rembersement{" +
                "Id=" + id +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", users='" + users + '\'' +
                '}';
    }
    
}