package com.revature.ers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ers.model.Reimbursement;
import com.revature.ers.repository.ReimbursementRepository;

@Service
public class ReimbursementServices {

    @Autowired
    private ReimbursementRepository reimbursementRepository;

    public List<Reimbursement> getAllReimbursementByUser(Long user) {
        return reimbursementRepository.findByUsers(user);
    }

    public Reimbursement createReimbersement(Reimbursement reimbursement) {
        return reimbursementRepository.save(reimbursement);
    }

    public List<Reimbursement> getPendingReimbursementsByUser(Long user) {
        return reimbursementRepository.getPendingReimbursementsByUsers(user);
    }

    public List<Reimbursement> getReimbursementsByStatus(String status) {
        return reimbursementRepository.findByStatus(status);
    }

    public void updateReimbursementStatus(Long user, String status) {
        reimbursementRepository.updateStatus(user, status);
    }

    public void deleteReimbursementByUser(Long user) {
        reimbursementRepository.deleteReimbursementByUsers(user);
    }
    
}
