package com.example.CloudBankAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CloudBankAPI.Model.Log;
import com.example.CloudBankAPI.Model.User;
import com.example.CloudBankAPI.Service.LogServices;
import com.example.CloudBankAPI.Service.UserService;

@RestController
@RequestMapping("bank")
public class UserController {
    @Autowired
    LogController bankAPILogController;
    LogServices logServices;
    UserService userService;

    public UserController(LogServices logServices, UserService userService, LogController bankAPILogController) {
        this.logServices = logServices;
        this.userService = userService;
        this.bankAPILogController = bankAPILogController;
    }

    @GetMapping("getalljson")
    public List<User> list() {
        return userService.listAllUsers();
    }

    @GetMapping(path = "/")
    public String Checking() {
        return "new SomeData()";
    }

    @GetMapping(path = "/getallusers")
    public String getall(){
        List<User> list = userService.listAllUsers();
        String jsn = "";
        for(User user : list){
            jsn = jsn + "{" + user.getId() + ", " + user.getFirstName() + ", " + user.getLastName() + ", " + user.getUserName() + ", " + user.getPassword() + "},";
        }
        return jsn;
    }

    //Add User
    @PostMapping(path = "/adduser", consumes = {"application/json"})
    public String addUser(@RequestBody User user){
        //if there is no active parameter in json file or active parameter is false
        if(user.isActive()==false){
            return "please add active boolean as a parameter to your json file or reactivate your notActive Account";
        }
        else{
            //if account active is true but first name is not added as a parameter
            if(user.getFirstName()==null){
                return "please add firstName String as a parameter to your json file";
            }
            else{
                //if firstName is added as a parameter but is kept blank 
                if(user.getFirstName().equals("")){
                    return "please don't leave your name as blank enter your name";
                }
                else{
                    //if firstName is written and lastName is not added as a parameter
                    if(user.getLastName()==null){
                        return "please add lastName String as a parameter to your json file";
                    }
                    else{
                        //if lastName is added as a parameter but kept blank
                        if(user.getLastName().equals("")){
                            return "please don't leave your name as blank enter your last name";
                        }
                        else{
                            //if userName as a parameter is not added
                            if(user.getUserName() == null){
                                return "please add userName String as a parameter to your json file";
                            }
                            else{
                                //Handling Exception for not finding username inside the database
                                try{
                                    User u = userService.getUserByUsername(user.getUserName());
                                    //if username found inside the database 
                                    if(u.getUserName().equals(user.getUserName())){
                                        return "Please change the Username as it is alrady in use";
                                    }
                                    else{
                                        return "account added";
                                    }
                                }
                                //catch has nothing
                                catch(Exception ex){
                                }
                                //As catch has nothing it comes out of try catch
                                //if password is not added as a parameter 
                                if(user.getPassword() == null){
                                    return "please add password String as a parameter to your json file";
                                }
                                else{
                                    //if password is not given as a parameter to json file
                                    if(user.getPassword() == null){
                                        return "please add password String as a parameter to your json file";
                                    }
                                    else{
                                        if(user.getPassword().length()>20 || user.getPassword().length()<8){
                                            return "Your password is not Strong, please use 8-20 letters only";
                                        } 
                                        else{
                                            if(user.getBalance()<400 || user.getBalance()>2000){
                                                return "please add balance double as a parameter to your json file, keep balance between 400-2000";
                                            }
                                            else{
                                                if(user.getPhone()==null){
                                                    return "please add phone String as a parameter to your json file";
                                                }
                                                else{
                                                    if(user.getPhone().length()!=10){
                                                        return "Please enter correct number";
                                                    }
                                                    else{
                                                        if(user.getEmail_Id()==null){
                                                            return "please add email_Id String as a parameter to your json file";
                                                        }
                                                        else{
                                                            if(user.getEmail_Id().equals("")){
                                                                return "please enter correct email id";
                                                            }
                                                            else{
                                                                userService.saveUser(user);
                                                                Log log = new Log(java.time.LocalDate.now().toString(), java.time.LocalTime.now().toString(), user.getUserName(), user.getUserName(), "Deposit", user.getBalance(), "New Account Created");
                                                                bankAPILogController.createLog(log);
                                                                return "Account has been created for" + user.getUserName();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }                                    
                                }
                            }
                        }
                    }
                }
            }
        }
        // userService.saveUser(user);
    }

    // Check for User using password
    @PostMapping(path = "/authuserusingidandpassowrd", consumes = { "application/json" })
    public boolean authenticationUsingId(@RequestBody User user) {
        User u = userService.getUser(user.getId());
        if ( u.getId() == user.getId() && u.getPassword().equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    //Check for User using username
    @PostMapping(path = "/authuserusingusernameandpassowrd", consumes = { "application/json" })
    public boolean authenticationUsingUsername(@RequestBody User user) {
        System.out.println("Hello "+user.getUserName());
        User u = userService.getUserByAuth(user.getUserName(), user.getPassword());
        if ( u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())) {
            System.out.println("found");
            return true;
        } else {
            System.out.println("not found");
            return false;
        }
    }

    //Update Account
    @PostMapping(path = "/update", consumes = {"application/json"})
    public String update(@RequestBody User user){
        if(user.getFirstName()==null && user.getLastName()==null && user.getUserName()==null && user.getPassword()==null && user.getPhone()==null){
            userService.updateEmailId(user);
        }
        else if(user.getFirstName()==null && user.getLastName()==null && user.getUserName()==null && user.getPassword()==null && user.getEmail_Id()==null){
            userService.updatePhone(user);
        }
        else if(user.getFirstName()==null && user.getLastName()==null && user.getUserName()==null && user.getEmail_Id()==null && user.getPhone()==null){
            userService.updatePassword(user);
        }
        else if(user.getFirstName()==null && user.getLastName()==null && user.getPassword()==null && user.getEmail_Id()==null && user.getPhone()==null){
            userService.updateUserName(user);
        }
        else if(user.getFirstName()==null && user.getPassword()==null && user.getUserName()==null && user.getEmail_Id()==null && user.getPhone()==null){
            userService.updateLastName(user);;
        }
        else{
            userService.updateFirstName(user);
        }
        

        return "User has been Updated Successfully..";
    }

    //show Balance
    @PostMapping(path = "/showbalance", consumes = {"application/json"})
    public String showBalance(@RequestBody User user){
        User u = userService.getUserByUsername(user.getUserName());
        if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
            float balance = u.getBalance();
            return "Your balance is " + balance;
        }
        else{
            return "Enter correct Username/Password";
        }
    }
    
    //Deposit Money using Username
    @PostMapping(path = "/DepositMoney/{deposit}", consumes = {"application/json"})
    public String depositMoney(@PathVariable float deposit, @RequestBody User user){
        User u = userService.getUserByUsername(user.getUserName());
        if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
            float balance = u.getBalance();
            balance = balance + deposit;
            u.setBalance(balance);
            userService.updateBalance(u);
            Log log = new Log(java.time.LocalDate.now().toString(), java.time.LocalTime.now().toString(), user.getUserName(), user.getUserName(), "Deposit", deposit, "Deposit Money");
            bankAPILogController.createLog(log);
            return "Balance has been updated "+balance;
        }else{
            return "Enter correct Username/Password";
        }
        
    }

    //Withdraw Money using Username
    @PostMapping(path = "/WithdrawMoney/{withdraw}", consumes = {"application/json"})
    public String withdrawMoney(@PathVariable float withdraw, @RequestBody User user){
        User u = userService.getUserByUsername(user.getUserName());
        if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
            float balance = u.getBalance();
            balance = balance - withdraw;
            u.setBalance(balance);
            userService.updateBalance(u);
            Log log = new Log(java.time.LocalDate.now().toString(), java.time.LocalTime.now().toString(), user.getUserName(), user.getUserName(), "Withdraw", withdraw, "Withdraw Money");
            bankAPILogController.createLog(log);
            return "Balance has been updated " + balance; 
        }else{
            return "Enter correct Username/Password";
        }
        
    }

    //send Money to another account in the same bank
    // Give the username of the person you want to send money to
    @PostMapping(path = "/sendMoney/{amount}/{usern}", consumes = {"application/json"})
    public String sendMoney(@PathVariable float amount, @PathVariable String usern, @RequestBody User user){
        User u = userService.getUserByUsername(user.getUserName());
        if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
            User us = userService.getUserByUsername(usern);
            float balance = us.getBalance();
            balance = balance + amount;
            us.setBalance(balance);
            userService.updateBalance(us);
            float deductbalance = u.getBalance();
            deductbalance = deductbalance - amount;
            u.setBalance(deductbalance);
            userService.updateBalance(u);
            Log log = new Log(java.time.LocalDate.now().toString(), java.time.LocalTime.now().toString(), user.getUserName(), us.getUserName(), "Send Money", amount, user.getUserName()+" sent " + amount + " amount of money to "+ us.getUserName());
            bankAPILogController.createLog(log);
            return "Money has been sent Rs " + amount;
        }else{
            return "Enter correct Username/Password";
        }
        
    }

    @DeleteMapping(path = "/deleteuser", consumes = {"application/json"})
    public String deleteUser(@RequestBody User user) {
        User u = userService.getUserByUsername(user.getUserName());
        if(u != null && u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())) {
            if(u.getBalance() == 0) {
                System.out.println(u.getId());
                userService.deleteByUserName(user.getUserName());
                Log log = new Log(java.time.LocalDate.now().toString(), java.time.LocalTime.now().toString(), user.getUserName(), user.getUserName(), "Delete Account", 0, "Account Deleted");
                bankAPILogController.createLog(log);
                return "User " + user.getUserName() + " has been Deleted Permanentaly!";
            } else {
                return "Please Remove your balance of amount Rs " + user.getBalance() + " before deleting your account";
            }
        } else {
            return "Please Enter correct Username and Password";
        }
    }


}
