package com.example.mygymclub.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mygymclub.models.EvaluationEntity;
import com.example.mygymclub.models.UserEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface EvaluationDao {
    @Query("SELECT id, date, weight, user_id FROM evaluations WHERE user_id = :userId")
    List<EvaluationEntity> findAll (long userId);

    @Query("SELECT id, date, weight, user_id FROM evaluations WHERE user_id = :userId AND date BETWEEN :from AND :to")
    List<EvaluationEntity> findByRange(Date from, Date to, long userId);

    @Insert
    long insert(EvaluationEntity evaluation);

    @Query("DELETE FROM evaluations WHERE id = :id")
    void delete(long id);
}
