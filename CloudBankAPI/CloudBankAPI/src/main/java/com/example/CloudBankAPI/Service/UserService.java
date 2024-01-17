package com.example.CloudBankAPI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CloudBankAPI.Model.User;
import com.example.CloudBankAPI.Repository.BankAPIRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService{
    @Autowired
    private BankAPIRepository bankapirepository;

    public UserService(BankAPIRepository bankAPIRepository) {
        this.bankapirepository = bankAPIRepository;
    }
    
    public List<User> listAllUsers(){
        return bankapirepository.findAll();
    }
    
    public void saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("cloudVendor must not be null");
        }
        bankapirepository.save(user);
    }
    
    public User getUser(Integer id) {
        if(id != null) {
            return bankapirepository.findById(id).get();
        } else {
            return null;
        }
    }
    @Transactional
    public void deleteUser(Integer id) {
        if(id != null) {
            bankapirepository.deleteById(id);;
        } else {
            throw new IllegalArgumentException("Invalid user Id");
        }
    }
    
    public User getUserByPassword(String password){
        return bankapirepository.findByPassword(password).get();
    }
    
    public User getUserByUsername(String username){
        return bankapirepository.findByUserName(username).get();
    }

    public User getUserByAuth(String username, String password){
        User user = bankapirepository.findByUserName(username).get();
        User pass = bankapirepository.findByPassword(password).get();
        
        if(user==pass){
            return user;
        }
        else{
            return user;
        }
    }
    
    public User getUserByFirstname(String firstname){
        return bankapirepository.findByFirstName(firstname).get();
    }
    
    public User getUserByLastname(String lastname){
        return bankapirepository.findByLastName(lastname).get();
    }

    public void updateUserName(User user){
        User bankuser = bankapirepository.findById(user.getId()).get();
        bankuser.setUserName(user.getUserName());
        bankapirepository.save(bankuser);
    }
    
    public void updateFirstName(User user){
        User bankuser = bankapirepository.findById(user.getId()).get();
        bankuser.setFirstName(user.getFirstName());
        bankapirepository.save(bankuser);
    }

    public void updateLastName(User user){
        User bankuser = bankapirepository.findById(user.getId()).get();
        bankuser.setLastName(user.getLastName());
        bankapirepository.save(bankuser);
    }

    public void updatePassword(User user){
        User bankuser = bankapirepository.findById(user.getId()).get();
        bankuser.setPassword(user.getPassword());
        bankapirepository.save(bankuser);
    }

    public void updatePhone(User user){
        User bankuser = bankapirepository.findById(user.getId()).get();
        bankuser.setPhone(user.getPhone());
        bankapirepository.save(bankuser);
    }

    public void updateEmailId(User user){
        User bankuser = bankapirepository.findById(user.getId()).get();
        bankuser.setEmail_Id(user.getEmail_Id());
        bankapirepository.save(bankuser);
    }

    public void updateBalance(User user){
        User bankuser = bankapirepository.findByUserName(user.getUserName()).get();
        bankuser.setBalance(user.getBalance());
        bankapirepository.save(bankuser);
    }

    public void deleteByUserName(String username) {
        User bankUser = bankapirepository.findByUserName(username).get();
        bankapirepository.deleteById(bankUser.getId());
    }
}
