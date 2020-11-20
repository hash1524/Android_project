package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SetAccountPage extends AppCompatActivity {
    EditText name,email,username,pass1,pass2;
    Button create,tologin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_account_page);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        username=findViewById(R.id.username1);
        create=findViewById(R.id.create);
        pass1=findViewById(R.id.passSet);
        pass2=findViewById(R.id.passRetyp);
        tologin=findViewById(R.id.tologin);
        DB=new DBHelper(this);
        tologin=findViewById(R.id.tologin);
        tologin.setVisibility(View.INVISIBLE);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String password=pass1.getText().toString();
                String repassword=pass2.getText().toString();
                if(username.getText().toString().equals("")||pass1.getText().toString().equals("")||pass2.getText().toString().equals("")){
                    Toast.makeText(SetAccountPage.this,"Details should'nt be empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass1.getText().toString().equals(repassword)){
                        Boolean checkuser=DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert=DB.insertData(user,password);
                            if(insert==true){
                                Toast.makeText(SetAccountPage.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SetAccountPage.this,"Registration failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SetAccountPage.this,"User already exists, kindly login",Toast.LENGTH_SHORT).show();
                            tologin.setVisibility(View.VISIBLE);
                        }
                    }
                    else{
                        Toast.makeText(SetAccountPage.this,"passwords dont match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SetAccountPage.this,LoginPage.class);
                startActivity(intent1);
            }
        });
    }
}