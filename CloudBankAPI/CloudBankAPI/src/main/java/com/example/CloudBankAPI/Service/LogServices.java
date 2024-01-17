package com.example.CloudBankAPI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CloudBankAPI.Model.Log;
import com.example.CloudBankAPI.Repository.BankAPILogRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LogServices {
    @Autowired
    private BankAPILogRepository bankapilog;

    public LogServices(BankAPILogRepository bankapilog) {
        this.bankapilog = bankapilog;
    }

    public List<Log> listAllUsers(){
        return bankapilog.findAll();
    }

    public void saveLog(Log log) {
        if(log != null) {
            bankapilog.save(log);
        }
    }
    
    public Log getLog(Integer id) {
        if(id != null) {
            return bankapilog.findById(id).get();    
        }else {
            return null;
        }
    }

    public List<Log> getAllLogs() {
        return bankapilog.findAll();
    }

    public Log getLogUsingUsername(String from) {
        return bankapilog.findByFrom(from).get();
    }
    
    public void deleteLog(Integer id) {
        if(id != null) {
            bankapilog.deleteById(id);
        }
    }

}
