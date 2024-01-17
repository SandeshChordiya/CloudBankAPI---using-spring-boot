package com.example.CloudBankAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CloudBankAPI.Model.Log;

public interface BankAPILogRepository extends JpaRepository<Log, Integer> {
    Optional<Log> findByFrom(String from);
}
