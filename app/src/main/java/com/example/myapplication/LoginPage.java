package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


public class LoginPage extends AppCompatActivity {
    EditText username,pass;
    Button login;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        username= findViewById(R.id.username);
        pass=findViewById(R.id.password);
        login=findViewById(R.id.login_1);
        DB=new DBHelper(this);
        login.setOnClickListener(v -> {
            String user=username.getText().toString();
            String password=pass.getText().toString();
            if(user.equals("")||password.equals("")){
                Toast.makeText(LoginPage.this, "Don't leave the spaces empty", Toast.LENGTH_SHORT).show();
            }
            else{
                Boolean checkuserpass=DB.checkusernamepassword(user,password);
                if(checkuserpass){
                    Toast.makeText(LoginPage.this,"Login successfull",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginPage.this,Dashboard.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginPage.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}