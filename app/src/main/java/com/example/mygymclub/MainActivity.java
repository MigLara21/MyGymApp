package com.example.mygymclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygymclub.controllers.AuthController;
import com.example.mygymclub.controllers.EvaluationController;
import com.example.mygymclub.models.Evaluation;
import com.example.mygymclub.models.User;
import com.example.mygymclub.ui.DatePickerFragment;
import com.example.mygymclub.ui.EvaluationAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilFrom, tilTo;
    private TextView tvTitle, tvClearFilter;
    private ListView lvAllEvaluation;
    private Button btnNewEvaluation, btnLogout, btnFilter;
    private AuthController authController;
    private EvaluationController evaluationController;
    private final String DATE_PATTERN = ("yyyy-MM-dd");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authController = new AuthController(this);
        evaluationController = new EvaluationController(this);

        tvTitle = findViewById(R.id.activity_main_tv_title);
        tvClearFilter = findViewById(R.id.activity_main_filter_clear);
        tilFrom = findViewById(R.id.activity_main_til_from);
        tilTo = findViewById(R.id.activity_main_til_to);
        lvAllEvaluation = findViewById(R.id.activity_main_lv_all_evaluation);
        btnNewEvaluation = findViewById(R.id.activity_main_btn_new_evaluation);
        btnLogout = findViewById(R.id.activity_main_btn_logout);
        btnFilter = findViewById(R.id.activity_main_btn_filter_clear);

        User user = authController.getUserSession();

        tvTitle.setText(String.format("Hola %s", user.getFirstName()));

       List<Evaluation> evaluationList = evaluationController.getAll();

        tilFrom.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilFrom);

        });

        tilTo.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilTo);

        });


        EvaluationAdapter adapter = new EvaluationAdapter(this, evaluationList);

        lvAllEvaluation.setAdapter(adapter);

        lvAllEvaluation.setOnItemClickListener(((adapterView, view, index, id)->{
            Evaluation evaluation = evaluationList.get(index);
            Intent i = new Intent(view.getContext(), EvaluationDetailActivity.class);
            i.putExtra("evaluation", evaluation);
            view.getContext().startActivity(i);
        }));


        btnNewEvaluation.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), NewEvaluationActivity.class);
            startActivity(i);
            finish();
        });


        btnLogout.setOnClickListener(view -> { authController.logout();});

        btnFilter.setOnClickListener(view ->{
            String fromStr = tilFrom.getEditText().getText().toString();
            String toStr = tilTo .getEditText().getText().toString();

            boolean validFrom = !fromStr.isEmpty();
            boolean validTo = !toStr.isEmpty();

            if (validFrom && validTo){

                SimpleDateFormat dateformatter = new SimpleDateFormat(DATE_PATTERN);

                try {
                    Date from = dateformatter.parse(fromStr);
                    Date to = dateformatter.parse(toStr);

                    List<Evaluation> evaluationRangeList = evaluationController.getRange(from, to);
                    EvaluationAdapter rangeAdapter = new EvaluationAdapter(this, evaluationRangeList);

                    lvAllEvaluation.setAdapter(rangeAdapter);

                    lvAllEvaluation.setOnItemClickListener(((adapterView, rangeView, index, id)->{
                        Evaluation evaluation = evaluationRangeList.get(index);
                        Intent i = new Intent(rangeView.getContext(), EvaluationDetailActivity.class);
                        i.putExtra("evaluation", evaluation);
                        rangeView.getContext().startActivity(i);
                    }));

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

        tvClearFilter.setOnClickListener(v -> {
            tilFrom.getEditText().setText("");
            tilTo.getEditText().setText("");
            lvAllEvaluation.setAdapter(adapter);

        });

    }

    /*@Override
    protected void onResume() {
        super.onResume();
        List<Evaluation> evaluationList = evaluationController.getAll();
        EvaluationAdapter adapter = new EvaluationAdapter(this, evaluationList);

        lvAllEvaluation.setAdapter(adapter);

        lvAllEvaluation.setOnItemClickListener(((adapterView, view, index, id)->{
            Evaluation evaluation = evaluationList.get(index);
            Intent i = new Intent(view.getContext(), EvaluationDetailActivity.class);
            i.putExtra("evaluation", evaluation);
            view.getContext().startActivity(i);
        }));*/
}
