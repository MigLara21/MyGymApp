package com.example.mygymclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygymclub.controllers.AuthController;
import com.example.mygymclub.controllers.EvaluationController;
import com.example.mygymclub.models.Evaluation;
import com.example.mygymclub.models.User;
import com.example.mygymclub.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewEvaluationActivity extends AppCompatActivity {


    private TextInputLayout tilId, tilDate, tilWeight;
    private Button btnRegisterEvaluation, btnBack;
    private final String DATE_PATTERN = ("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_evaluation);

        tilDate = findViewById(R.id.activity_new_evaluation_til_date);
        tilWeight = findViewById(R.id.activity_new_evaluation_til_weight);
        btnRegisterEvaluation = findViewById(R.id.activity_evaluation_btn_register_evaluation);
        btnBack = findViewById(R.id.activity_evaluation_btn_back);

        tilDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate);

        });

        btnRegisterEvaluation.setOnClickListener(view -> {

            String Date = tilDate.getEditText().getText().toString();
            String Weight = tilWeight.getEditText().getText().toString();

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

            Date dueDate = null;
            try {
                dueDate = dateFormat.parse(Date);
            }catch (ParseException e){
                e.printStackTrace();

            }

            Double datoWeight = 0.0;
            try {
                datoWeight = Double.parseDouble(Weight);
                tilWeight.setError(null);
                tilWeight.setErrorEnabled(false);
            } catch (Exception error) {
                tilWeight.setError("El peso es inválido");
                return;
            }

            AuthController authController = new AuthController(view.getContext());
            User user = authController.getUserSession();

            Evaluation evaluation = new Evaluation(dueDate,datoWeight, user.getId());

            EvaluationController controller = new EvaluationController(view.getContext());

            controller.register(evaluation);

            Toast.makeText(view.getContext(), "Registrando Evaluación", Toast.LENGTH_SHORT).show();


        });

        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }
}