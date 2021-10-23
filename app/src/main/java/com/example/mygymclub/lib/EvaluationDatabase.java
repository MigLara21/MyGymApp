package com.example.mygymclub.lib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mygymclub.dao.EvaluationDao;
import com.example.mygymclub.dao.UserDao;
import com.example.mygymclub.models.EvaluationEntity;
import com.example.mygymclub.models.UserEntity;
import com.example.mygymclub.utils.Converters;

@Database(entities = {UserEntity.class, EvaluationEntity.class}, version = 4)
@TypeConverters({Converters.class})
public abstract class EvaluationDatabase extends RoomDatabase {
    private static final String DB_NAME = "evaluation_database";
    private static EvaluationDatabase instance;

    public static synchronized EvaluationDatabase getInstance(Context ctx) {
        if(instance == null) {
            instance = Room.databaseBuilder(ctx.getApplicationContext(), EvaluationDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
    public abstract EvaluationDao evaluationDao();
}
