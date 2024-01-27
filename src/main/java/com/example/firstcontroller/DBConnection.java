package com.example.firstcontroller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBConnection {

    private Connection connection;
    private List<Client> Clients = new ArrayList<>();

    @PostConstruct
     public void init(){
        createDBConnection("root","1234");

    }

    public List<Client> getClients() {
        return Clients;
    }

    public void setClients(List<Client> clients) {
        Clients = clients;
    }

    private void createDBConnection(String username , String password)  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2024", username, password);
            System.out.println("Connection is success");
            this.newList();
        }
        catch (Exception e) {
            System.err.println("Failed to connect to database");
        }
    }
    public boolean insertUser(Client client){
        boolean success = false;
        try{
            if(this.checkIfUserAvailable(client.getUserName())){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(username , password) VALUES (?,?)");
            preparedStatement.setString(1,client.getUserName());
            preparedStatement.setString(2 ,client.getPassword());
            preparedStatement.executeUpdate();
            success =true;
            Clients = new ArrayList<>();
            this.newList();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }
    public boolean insertProduct(product product){
        boolean success = false;
        try{
            if(this.checkIfProductExists(product.getDescription())){
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products(description,price,count) VALUES (?,?,?)");
                preparedStatement.setString(1,product.getDescription());
                preparedStatement.setFloat(2 , product.getPrice());
                preparedStatement.setInt(3 , product.getCount());
                preparedStatement.executeUpdate();
                success =true;}
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
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
    public boolean checkIfProductExists(String description){
        boolean exists =true;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT description FROM products WHERE description=?");
            preparedStatement.setString(1, description);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                exists=false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return exists;
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
    private void newList(){
        try{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT username,password,id FROM users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String nameFromDb = resultSet.getString(1);
            String passwordFromDb = resultSet.getString(2);
            int idFromDb = resultSet.getInt(3);
            Client client = new Client (nameFromDb,passwordFromDb,idFromDb);
            Clients.add(client);
        }}
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
