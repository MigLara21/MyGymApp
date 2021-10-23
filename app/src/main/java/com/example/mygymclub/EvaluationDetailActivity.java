package com.example.mygymclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.mygymclub.controllers.AuthController;
import com.example.mygymclub.controllers.EvaluationController;
import com.example.mygymclub.models.Evaluation;
import com.example.mygymclub.models.User;

import java.text.SimpleDateFormat;

public class EvaluationDetailActivity extends AppCompatActivity {

    private Button btnMain, btnDelete;
    private TextView tvId, tvWeight, tvDate, tvImc;
    private final String DATE_PATTERN = "yyyy-MM-dd";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_detail);

        Evaluation evaluation = (Evaluation) getIntent().getSerializableExtra("evaluation");



        tvId = findViewById(R.id.activity_detail_tv_id);
        tvWeight = findViewById(R.id.activity_detail_tv_weight);
        tvDate = findViewById(R.id.activity_detail_tv_date);
        tvImc = findViewById(R.id.activity_detail_tv_imc);
        btnMain = findViewById(R.id.activity_detail_btn_main);
        btnDelete = findViewById(R.id.activity_detail_btn_delete);



        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
        String date = dateFormatter.format(evaluation.getDate());

        AuthController authController = new AuthController(this);
        User user = authController.getUserSession();

        tvId.setText(String.format("N° de Evaluación : %s", evaluation.getId()));
        tvWeight.setText(String.format("Peso : %s", evaluation.getWeight()));
        tvDate.setText(String.format("Fecha : %s", date));
        tvImc.setText(String.format("Imc : %s", evaluation.calculateImcString(user.getHeight())));



        btnDelete.setOnClickListener(view -> {
            EvaluationController controller = new EvaluationController(view.getContext());
            controller.delete(evaluation.getId());
        });

        btnMain.setOnClickListener(view ->{
            super.onBackPressed();

        });



    }
}