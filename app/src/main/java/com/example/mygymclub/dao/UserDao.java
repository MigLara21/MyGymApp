package com.example.mygymclub.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mygymclub.models.UserEntity;

@Dao
public interface UserDao {
    @Query("SELECT id, user_name, first_name, last_name, birthday, height, password FROM users WHERE user_name = :user LIMIT 1")
    UserEntity findByUser (String user);

    @Insert
    long insert(UserEntity user);
}
