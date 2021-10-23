package com.example.mygymclub.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Database;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "users", indices = {@Index(value = "user_name", unique = true)})
public class UserEntity implements IUser {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "birthday")
    private Date birthday;

    @ColumnInfo(name = "height")
    private double height;

    @ColumnInfo(name = "password")
    private String password;

    public UserEntity(long id, String userName, String firstName, String lastName, Date birthday, double height, String password) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.height = height;
        this.password = password;
    }

    public long getId(){return id; }

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


}

