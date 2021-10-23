package com.example.mygymclub.models;

import com.example.mygymclub.controllers.EvaluationController;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evaluation implements Serializable, IEvaluation {
    private long id;
    private double weight;
    private Date date;
    private long userId;

    public Evaluation(Date date, double weight, long userId) {
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

    public void setId(long id) {
        this.id = id;
    }

    public double calculateImc(double height){
        return (weight / (height * height)*10000);

    }

    public String calculateImcString(double height){
        return Double.toString(calculateImc(height));

    }
}