package com.example.mygymclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.mygymclub.controllers.AuthController;
import com.example.mygymclub.models.User;
import com.example.mygymclub.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    private final String DATE_PATTERN = ("yyyy-MM-dd");
    private TextInputLayout tilBirthday, tilUserName, tilFirstName, tilLastName, tilHeight, tilPassword;
    private Button btnRegister, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tilUserName = findViewById(R.id.activity_register_field_user_name);
        tilFirstName = findViewById(R.id.activity_register_field_first_name);
        tilLastName = findViewById(R.id.activity_register_field_last_name);
        tilHeight = findViewById(R.id.activity_register_field_height);
        tilPassword = findViewById(R.id.activity_register_field_password);
        tilBirthday = findViewById(R.id.activity_register_field_birthday);
        btnRegister = findViewById(R.id.activity_register_btn_register);
        btnback = findViewById(R.id.activity_register_btn_back);

        tilBirthday.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilBirthday);

        });


        btnRegister.setOnClickListener(view -> {
            String userName = tilUserName.getEditText().getText().toString();
            String firstName = tilFirstName.getEditText().getText().toString();
            String lastName = tilLastName.getEditText().getText().toString();
            String height = tilHeight.getEditText().getText().toString();
            String birthday = tilBirthday.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            SimpleDateFormat dateformatter = new SimpleDateFormat(DATE_PATTERN);

            boolean usernameValid = !userName.isEmpty();
            boolean passwordValid = !password.isEmpty();
            boolean firstnameValid = !firstName.isEmpty();
            boolean lastnameValid = !lastName.isEmpty();
            boolean heightValid = !height.isEmpty();
            boolean birthdayValid = !birthday.isEmpty();


            if (!usernameValid) {
                tilUserName.setError("Usuario no ingresado");
            } else {
                tilUserName.setError(null);
            }
            if (!firstnameValid) {
                tilFirstName.setError("Nombre no ingresado");
            } else {
                tilFirstName.setError(null);
            }
            if (!lastnameValid) {
                tilLastName.setError("Apellido no ingresado");
            } else {
                tilLastName.setError(null);
            }
            if (!passwordValid) {
                tilPassword.setError("Password requerida");
            } else {
                tilPassword.setError(null);
            }
            if (!heightValid) {
                tilHeight.setError("Altura no ingresada");
            } else {
                tilHeight.setError(null);
            }
            if (!birthdayValid) {
                tilBirthday.setError("Fecha no ingresada");
            } else {
                tilBirthday.setError(null);
            }

            Date birthdayDate = null;
            try {
                birthdayDate = dateformatter.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Double datoHeight = 0.0;
            try {
                datoHeight = Double.parseDouble(height);
                tilHeight.setError(null);
                tilHeight.setErrorEnabled(false);
            } catch (Exception error) {
                tilHeight.setError("La estatura es inválida");
                return;
            }

            User user = new User(userName, firstName, lastName, birthdayDate, datoHeight);
            user.setPassword(password);

            AuthController controller = new AuthController(view.getContext());
            controller.register(user);


        });

        btnback.setOnClickListener(view -> {
                Toast.makeText(view.getContext(),"",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), LoginActivity.class);
                startActivity(i);
                finish();
        });

    }

}

