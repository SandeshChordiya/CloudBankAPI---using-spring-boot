package com.example.CloudBankAPI.Controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    ArrayList<String> friends = new ArrayList<>();

    public MyController(){
        friends.add("Sandesh");
        friends.add("Rajesh");
        friends.add("Tejal");
        friends.add("Mamata");
        friends.add("Chordiya");
        friends.add("Hardik");
        friends.add("Akash");
        friends.add("Omkar");
        friends.add("Ajinkya");
        friends.add("Manish");
        friends.add("Prathamesh");
        friends.add("Roman Reigns");
        friends.add("Dean Ambrose");
        friends.add("Seth Rollins");
    }

    @GetMapping(path = "/")
    public String welcome(){
        return "Welcome to Friends";
    }

    @GetMapping(path = "showall")
    public String getallfriends(){
        String allfriends = " ";

        for(Iterator<String> iterator = friends.iterator(); iterator.hasNext();){
            String str = (String) iterator.next();
            allfriends = allfriends + " " + str;
        }
        return allfriends;
    }

    @GetMapping(path = "/findfriend/{find}")
    public String findFriendByName(@PathVariable String find){
        String names = " ";

        for(Iterator<String> iterator = friends.iterator(); iterator.hasNext();){
            String str = (String) iterator.next();
            if(find.equals(str)){
                names = names + " " + str;
            }
        }
        return "Result : "+ names;
    }

    @GetMapping(path = "/findbyfirstletter/{f_letter}")
    public String findFriendByFirstLetter(@PathVariable String f_letter){
        String names = " ";

        for(Iterator<String> iterator = friends.iterator(); iterator.hasNext();){
            String str = (String) iterator.next();
            if(f_letter.charAt(0) == str.charAt(0)){
                names = names + " " + str;
            }
        }
        return "Result : "+ names;
    }

    @GetMapping(path = "/findbyid/{id}")
    public String findById(@PathVariable int id){
        
        try
		{
			return "name : " + friends.get(id);
		}
		catch (IndexOutOfBoundsException e) {
			return "invalid Location";
		}
    }
   
}
