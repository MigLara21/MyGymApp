package com.example.mygymclub.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mygymclub.R;
import com.example.mygymclub.controllers.AuthController;
import com.example.mygymclub.models.Evaluation;
import com.example.mygymclub.models.User;

import java.text.SimpleDateFormat;
import java.util.List;

public class EvaluationAdapter extends BaseAdapter {
    private Context ctx;
    private List<Evaluation> EvaluationList;
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private AuthController authController;

    public EvaluationAdapter(Context ctx, List<Evaluation> evaluationList) {
        this.ctx = ctx;
        EvaluationList = evaluationList;
    }

    @Override
    public int getCount() {
        return EvaluationList.size();
    }

    @Override
    public Object getItem(int i) {
        return EvaluationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return EvaluationList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);

        view = inflater.inflate(R.layout.item_evaluation, null);

        authController = new AuthController(ctx);
        User user = authController.getUserSession();

        Evaluation evaluation = EvaluationList.get(i);

        TextView tvId = view.findViewById(R.id.item_evaluation_tv_id);
        TextView tvDate = view.findViewById(R.id.item_evaluation_tv_date);
        TextView tvWeight = view.findViewById(R.id.item_evaluation_tv_weight);
        TextView tvImc = view.findViewById(R.id.item_evaluation_tv_imc);


        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
        String date = dateFormatter.format(evaluation.getDate());

        tvId.setText(String.format("Id : %s", Long.toString(evaluation.getId())));
        tvDate.setText(String.format("Fecha : %s", date));
        tvWeight.setText(String.format(" Peso : %s", Double.toString(evaluation.getWeight())));
        tvImc.setText(String.format("IMC : %s", evaluation.calculateImcString(user.getHeight())));


        return view;
    }
}
