package com.example.firstcontroller;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName,password;
    private List<String> notes;

    public User(String name , String pass) {
        this.userName = name;
        this.password = pass;
        this.notes = new ArrayList<>();
    }

    public String getUserName() {return userName;
    }

    public void setUserName(String userName) {this.userName = userName;
    }

    public String getPassword() {return password;
    }

    public void setPassword(String password) {this.password = password;
    }

    public boolean isSameUser(String userName, String password){
        return this.userName.equals(userName) && this.password.equals(password);
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public boolean addNote (String note){
        boolean notFind = true;
        for (String x : this.notes) {
            if(x.equals(note)) notFind = false;
        }
        if(notFind) this.notes.add(note);
        return notFind;
    }
}
