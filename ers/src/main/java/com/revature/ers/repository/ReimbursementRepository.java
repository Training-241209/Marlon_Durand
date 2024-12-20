package com.revature.ers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.ers.model.Reimbursement;

public interface ReimbursementRepository extends JpaRepository<Reimbursement, Long> {

    List<Reimbursement> findByUsers (Long user);
    
    List<Reimbursement> findByStatus(String status);
    
    @Query("SELECT r FROM Reimbursement  r WHERE r.users = :users AND r.status = 'Pending'")
    List<Reimbursement> getPendingReimbursementsByUsers(Long users);

    @Modifying
    @Query("UPDATE Reimbursement SET status = :status WHERE id = :id")
    void updateStatus(Long id, String status);

    @Modifying
    @Query("DELETE FROM Reimbursement WHERE users = :users")
    void deleteReimbursementByUsers(Long users);

}