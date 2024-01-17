package com.example.CloudBankAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CloudBankAPI.Model.User;

public interface BankAPIRepository extends JpaRepository<User, Integer>{
    Optional<User> findByPassword(String password);
    Optional<User> findByUserName(String username);
    Optional<User> findByFirstName(String firstname);
    Optional<User> findByLastName(String lastname);
    Optional<User> deleteByUserName(String username);
}
