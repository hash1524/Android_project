package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RegisterPage extends AppCompatActivity {

    Button Generate_OTP,Verify;
    EditText Email,Phone_number;
    PinView pinview;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        Generate_OTP=(Button) findViewById(R.id.generateOTP);
        Verify=(Button) findViewById(R.id.Verify);
        Email=(EditText) findViewById(R.id.mailid);
        Phone_number=(EditText) findViewById(R.id.phone);
        pinview=(PinView) findViewById(R.id.PinView);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);

        Generate_OTP.setOnClickListener(v -> {
            if(Phone_number.getText().toString().trim().isEmpty()){
                Toast.makeText(RegisterPage.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            Generate_OTP.setVisibility(View.INVISIBLE);
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91"+Phone_number.getText().toString(),
                    10,
                    TimeUnit.SECONDS,
                    RegisterPage.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            progressBar.setVisibility(View.GONE);
                            Generate_OTP.setVisibility(View.VISIBLE);
                            Toast.makeText(RegisterPage.this, "Verified Successfully", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            progressBar.setVisibility(View.GONE);
                            Generate_OTP.setVisibility(View.VISIBLE);
                            Toast.makeText(RegisterPage.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String VerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            progressBar.setVisibility(View.GONE);
                            Generate_OTP.setVisibility(View.VISIBLE);
                            Intent intent =new Intent(getApplicationContext(),VerificationPage.class);
                            intent.putExtra("phone",Phone_number.getText().toString());
                            intent.putExtra("VerificationId",VerificationId);
                            startActivity(intent);
                        }
                    }
            );
        });




    }
}