package com.example.mygymclub.models;

import java.util.Date;

public interface IUser {

    long getId();
    String getUserName();
    String getFirstName();
    String getLastName();
    Date getBirthday();
    double getHeight();
    String getPassword();

}
