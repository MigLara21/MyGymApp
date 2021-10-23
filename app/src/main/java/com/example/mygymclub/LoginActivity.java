package com.example.mygymclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.mygymclub.controllers.AuthController;
import com.example.mygymclub.models.User;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;
    private TextInputLayout tilUser, tilPassword;
    private AuthController authController;
    private final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authController = new AuthController(this);

        btnLogin = findViewById(R.id.activity_login_btn_login);
        btnRegister = findViewById(R.id.activity_login_btn_register);
        tilUser = findViewById(R.id.activity_login_field_user);
        tilPassword = findViewById(R.id.activity_login_field_password);

        btnLogin.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Iniciando sesión", Toast.LENGTH_SHORT).show();

            String user = tilUser.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            boolean userValid = !user.isEmpty();
            boolean passwordValid = !password.isEmpty();

            if(!userValid){
                tilUser.setError("Campo requerido");

            }else{
                tilUser.setError(null);
                tilUser.setErrorEnabled(false);
            }

            if(!passwordValid){
                tilPassword.setError("Campo requerido");
            }else{
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            if(userValid && passwordValid) {
                authController.login(user, password);
            }else{
                Toast.makeText(view.getContext(), "Ambos Campos Inválidos", Toast.LENGTH_SHORT).show();

            }

            boolean dateRight = false;



        });

        btnRegister.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Vamos a Registrarnos!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), RegisterActivity.class);
            startActivity(i);
            finish();
        });
    }
}