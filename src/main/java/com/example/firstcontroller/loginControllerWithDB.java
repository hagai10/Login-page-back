package com.example.firstcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class loginControllerWithDB {
    @Autowired
    private DBConnection dbConnection;
    @RequestMapping(value = "check-username")
    public boolean checkUsername(String username){
        return dbConnection.checkIfUserAvailable(username);
    }
    @RequestMapping(value = "login-account")
    public boolean logToAccount(String username ,String password){
        return dbConnection.checkCredentials(username,password);
    }
    @RequestMapping(value = "get-users")
    public List<Client> getUsers(){
        return dbConnection.getClients();
    }
    @RequestMapping(value = "add-user")
    public boolean addUser(String name , String pass){
       Client clientToAdd = new Client (name,pass);
        return dbConnection.insertUser(clientToAdd);
    }
    @RequestMapping(value = "add-product")
    public boolean addProduct(String name , float price ,int count){
        product productToAdd = new product(name,price,count);
        return dbConnection.insertProduct(productToAdd);
    }

}
