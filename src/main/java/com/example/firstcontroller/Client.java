package com.example.firstcontroller;

public class Client {

    private String userName, password;
    private int id=0;

    public Client(String name, String pass) {
        this.userName = name;
        this.password = pass;
    }
    public Client(String name, String pass,int id) {
        this.userName = name;
        this.password = pass;
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSameUser(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }

}