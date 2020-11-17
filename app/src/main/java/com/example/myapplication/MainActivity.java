package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    Button Register,Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        Login=(Button) findViewById(R.id.buttonLogin);
        Login.setOnClickListener((v -> {
            Intent ToLoginPage=new Intent(MainActivity.this, LoginPage.class);
            startActivity(ToLoginPage);
        }));

        Register = (Button) findViewById(R.id.buttonRegister);
        Register.setOnClickListener((v -> {
            Intent ToRegisterPage=new Intent(MainActivity.this,RegisterPage.class);
            startActivity(ToRegisterPage);

        }));


    }

}