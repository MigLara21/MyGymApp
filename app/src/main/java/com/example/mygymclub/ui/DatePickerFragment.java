package com.example.mygymclub.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener;

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        return fragment;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }

    public static void showDatePickerDialog(AppCompatActivity activity, final TextInputLayout til) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(((DatePicker, year, month, day) -> {
            final String selectedDate = String.format("%d-%02d-%02d", year, (month + 1), day);
            til.getEditText().setText(selectedDate);
        }));
        newFragment.show(activity.getSupportFragmentManager(), "datePicker");

    }
}
