package com.revature.ers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.ers.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findUsersByUsersId(Long id);

    @Modifying
    @Query("DELETE FROM Users u WHERE u.usersId = :id")
    Void deleteUsersByUsersId(Long id);

    @Modifying
    @Query("UPDATE Users SET role = :role WHERE usersId = :id")
    Void updateUsersRoleByUsersId(Long id, int role);

    Users findUsersByUsername(String username);
    

}
