package com.example.mygymclub.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.text.AllCapsTransformationMethod;

import com.example.mygymclub.MainActivity;
import com.example.mygymclub.dao.EvaluationDao;
import com.example.mygymclub.lib.EvaluationDatabase;
import com.example.mygymclub.models.Evaluation;
import com.example.mygymclub.models.EvaluationEntity;
import com.example.mygymclub.models.EvaluationMapper;
import com.example.mygymclub.models.User;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluationController {


    private Context ctx;
    private EvaluationDao evaluationDao;

    public EvaluationController(Context ctx) {
        this.ctx = ctx;
        this.evaluationDao = EvaluationDatabase.getInstance(ctx).evaluationDao();
    }

    public void register(Evaluation evaluation){
        EvaluationMapper mapper = new EvaluationMapper(evaluation);
        EvaluationEntity newEvaluation = mapper.toEntity();
        evaluationDao.insert(newEvaluation);
        Intent i = new Intent(ctx, MainActivity.class);
        ctx.startActivity(i);
        ((Activity)ctx).finish();
        Toast.makeText(ctx,"Registrar", Toast.LENGTH_SHORT).show();


    }
    public void delete(Long id){
        DialogInterface.OnClickListener dialoClickListener = (dialog, which) ->{
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        evaluationDao.delete(id);
                        Intent i = new Intent(ctx, MainActivity.class);
                        ctx.startActivity(i);
                        ((Activity)ctx).finish();
                    }catch (Error error) {
                        error.printStackTrace();
                        Toast.makeText(ctx, "Error al eliminar la Evaluación", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Estas seguro que deseas eliminar la evaluación?")
                        .setPositiveButton("Si", dialoClickListener)
                        .setNegativeButton("No", dialoClickListener)
                        .show();

    }

    public List<Evaluation> getAll(){
        AuthController authController = new AuthController(ctx);
        User user = authController.getUserSession();
        List<EvaluationEntity> evaluationEntityList = evaluationDao.findAll(user.getId());
        List<Evaluation> evaluationList = new ArrayList<>();

        for(EvaluationEntity evaluationEntity : evaluationEntityList){
            EvaluationMapper mapper = new EvaluationMapper(evaluationEntity);
            Evaluation evaluation = mapper.toBase();
            evaluationList.add(evaluation);
        }
        return evaluationList;

    }

    public List<Evaluation> getRange(Date from, Date to){
        AuthController authController = new AuthController(ctx);
        User user = authController.getUserSession();
        List<EvaluationEntity> evaluationEntityList = evaluationDao.findByRange(from, to, user.getId());
        List<Evaluation> evaluationList = new ArrayList<>();

        for(EvaluationEntity evaluationEntity : evaluationEntityList){
            EvaluationMapper mapper = new EvaluationMapper(evaluationEntity);
            Evaluation evaluation = mapper.toBase();
            evaluationList.add(evaluation);
        }
        return evaluationList;

    }
}
