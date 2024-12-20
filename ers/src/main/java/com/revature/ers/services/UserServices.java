package com.revature.ers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ers.model.Users;
import com.revature.ers.repository.UserRepository;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public Users createUser (Users users) {
        return userRepository.save(users);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUsersById (Long id) {
        return userRepository.findUsersByUsersId(id);
    }

    public Users getUsersByUsername (String username) {
        return userRepository.findUsersByUsername(username);
    }

    public void deleteUserById (Long id) {
        userRepository.deleteById(id);
    }

    public void changeUsersRole (Long id,Integer role) {
        userRepository.updateUsersRoleByUsersId(id, role);
    }

}