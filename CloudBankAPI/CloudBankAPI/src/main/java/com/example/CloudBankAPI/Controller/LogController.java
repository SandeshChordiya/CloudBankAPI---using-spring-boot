package com.example.CloudBankAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CloudBankAPI.Model.Log;
import com.example.CloudBankAPI.Service.LogServices;

@RestController
@RequestMapping("banklog")
public class LogController {
    @Autowired
    LogServices logServices;

    public LogController(LogServices logServices) {
        this.logServices = logServices;
    }

    @GetMapping(path = "hellolog")
    public String helloLog() {
        return "Hello Welcome to Bank API Logs";
    }

    public void createLog(Log log) {
        logServices.saveLog(log);
        System.out.println("New Log has been Created");
    }

    @GetMapping(path = "/getalllogs")
    public List<Log> getAllLogs() {
        return logServices.getAllLogs();
    }

    
}
