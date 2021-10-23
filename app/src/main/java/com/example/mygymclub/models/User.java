package com.example.mygymclub.models;

import java.util.Date;

public class User implements IUser {


    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private Date birthday;
    private double height;
    private String password;

    public User(String userName, String firstName, String lastName, Date birthday, double height) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.height = height;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public double getHeight() {
        return height;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeightString(){
        return Double.toString(height);
    }
}