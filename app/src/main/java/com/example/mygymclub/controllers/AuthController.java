package com.example.mygymclub.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import com.example.mygymclub.LoginActivity;
import com.example.mygymclub.MainActivity;
import com.example.mygymclub.dao.UserDao;
import com.example.mygymclub.lib.BCrypt;
import com.example.mygymclub.lib.EvaluationDatabase;
import com.example.mygymclub.models.User;
import com.example.mygymclub.models.UserEntity;
import com.example.mygymclub.models.UserMapper;

import java.util.Date;


public class AuthController {
    private final String KEY_USER_ID = "userId";
    private final String KEY_USER_NAME = "userName";
    private final String KEY_FIRST_NAME = "userFirstName";
    private final String KEY_LAST_NAME = "userLastName";
    private final String KEY_HEIGHT = "userHeight";

    private UserDao userDao;
    private Context ctx;
    private SharedPreferences preferences;


    public AuthController(Context ctx) {
        this.ctx = ctx;
        int PRIVATE_MODE = 0;
        String PREF_NAME = "EvaluationAppPref";
        this.preferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.userDao = EvaluationDatabase.getInstance(ctx).userDao();
    }

    private void setUserSession(User user){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putLong(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_NAME, user.getUserName());
        editor.putString(KEY_FIRST_NAME, user.getFirstName());
        editor.putString(KEY_LAST_NAME, user.getLastName());
        editor.putString(KEY_HEIGHT, String.valueOf(user.getHeight()));
        editor.apply();

    }

    public User getUserSession(){
        long id = preferences.getLong(KEY_USER_ID, 0);
        String userName = preferences.getString(KEY_USER_NAME, "");
        String firstName = preferences.getString(KEY_FIRST_NAME,"");
        String lastName = preferences.getString(KEY_LAST_NAME,"");
        String height = preferences.getString(KEY_HEIGHT, "0.0");

        User user = new User(userName, firstName, lastName, new  Date(), Double.parseDouble(height));
        user.setId(id);
        return user;
    }

    public  void checkUserSession(){
        long id = preferences.getLong(KEY_USER_ID, 0);

        final int TIMEOUT = 2000;

        new Handler().postDelayed(()-> {
            if(id != 0){
                Toast.makeText(ctx,"Bienvenido", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ctx, MainActivity.class);
                ctx.startActivity(i);
            }else {
                Intent i = new Intent(ctx, LoginActivity.class);
                ctx.startActivity(i);
            }
            ((Activity)ctx).finish();

        }, TIMEOUT);

        }


    public void register(User user){
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        UserEntity userEntity = new UserMapper(user).toEntity();

        userDao.insert(userEntity);

        Toast.makeText(ctx, String.format("Usuario %s registrado", user.getUserName()), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(i);
    }

    public void login(String userName, String password){
        UserEntity userEntity = userDao.findByUser(userName);


        if (userEntity == null){
            Toast.makeText(ctx, "Usuario y/o Contraseña Invalidos", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new UserMapper(userEntity).toBase();

        if (BCrypt.checkpw(password, user.getPassword())){
            setUserSession(user);
            Toast.makeText(ctx, String.format("Bienvenido %s", user), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();
        } else {
            Toast.makeText(ctx, "Usuario y/o contraseña invalido", Toast.LENGTH_SHORT).show();
        }

    }

    public void logout(){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(ctx, "Cerrando sesión", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
        ((Activity)ctx).finish();

    }
}
