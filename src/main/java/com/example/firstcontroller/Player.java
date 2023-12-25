package com.example.firstcontroller;

public class Player {
    private String firstName,lastName;
    private int numOfGoals;

    public Player(String first, String last, int goals) {
        this.firstName = first;
        this.lastName=last;
        this.numOfGoals=goals;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumOfGoals() {
        return numOfGoals;
    }

    public void setNumOfGoals(int numOfGoals) {
        this.numOfGoals = numOfGoals;
    }
}

