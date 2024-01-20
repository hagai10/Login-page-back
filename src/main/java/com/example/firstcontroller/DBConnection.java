package com.example.firstcontroller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class DBConnection {

    private Connection connection;
    @PostConstruct
     public void init(){
        createDBConnection("root","1234");

    }
    private void createDBConnection(String username ,String password)  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2024", username, password);
            System.out.println("Connection is success");
        }
        catch (Exception e) {
            System.err.println("Failed to connect to database");
        }
    }
    public boolean checkIfUserAvailable(String username){
        boolean available =true;
        try{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM users WHERE username=?");
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            available=false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return available;
    }
    public boolean checkCredentials(String username, String password){
        boolean ok = false;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM users WHERE username=? AND password=?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                ok=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }
}
