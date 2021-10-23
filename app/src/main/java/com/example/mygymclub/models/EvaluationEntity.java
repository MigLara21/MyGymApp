package com.example.mygymclub.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "evaluations")

public class EvaluationEntity implements IEvaluation{
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "weight")
    private Double weight;

    @ColumnInfo(name = "user_id")
    private long userId;


    public EvaluationEntity(long id, Date date, Double weight, long userId) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.userId = userId;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public long getUserId() {
        return userId;
    }
}
