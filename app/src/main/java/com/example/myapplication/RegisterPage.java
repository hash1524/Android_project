package com.example.myapplication;


import android.annotation.SuppressLint;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RegisterPage extends AppCompatActivity {

    Button Generate_OTP,Verify;
    EditText Phone_number;
    PinView pinview;
    ProgressBar progressBar1,progressBar2;
    String code,verifyId;
    PinView pinView;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        Generate_OTP= findViewById(R.id.generateOTP);
        Verify= findViewById(R.id.Verify);
        Phone_number= findViewById(R.id.phone);
        pinview= findViewById(R.id.PinView);
        progressBar1= findViewById(R.id.progressbar1);
        Verify= findViewById(R.id.Verify);
        pinView= findViewById(R.id.PinView);
        progressBar2= findViewById(R.id.progressbar2);
        Generate_OTP.setOnClickListener(v -> {
            if(Phone_number.getText().toString().trim().isEmpty() || Phone_number.getText().toString().trim().length()!=10){
                Toast.makeText(RegisterPage.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                return;
            }
            progressBar1.setVisibility(View.VISIBLE);
            Generate_OTP.setVisibility(View.INVISIBLE);
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91"+Phone_number.getText().toString(),
                    10,
                    TimeUnit.SECONDS,
                    RegisterPage.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            progressBar1.setVisibility(View.GONE);
                            Generate_OTP.setVisibility(View.VISIBLE);
                            Toast.makeText(RegisterPage.this, "Verified Successfully", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            progressBar1.setVisibility(View.GONE);
                            Generate_OTP.setVisibility(View.VISIBLE);
                            Toast.makeText(RegisterPage.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onCodeSent(@NonNull String VerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            progressBar1.setVisibility(View.GONE);
                            Generate_OTP.setVisibility(View.VISIBLE);
                            verifyId=VerificationId;
                        }
                    }
            );
        });
        Verify.setOnClickListener(v -> {
            if(Objects.requireNonNull(pinView.getText()).toString().trim().length() != 6){
                Toast.makeText(RegisterPage.this, "Enter a valid OTP", Toast.LENGTH_SHORT).show();
            }
            code=pinView.getText().toString().trim();
            if(verifyId!=null){
                progressBar2.setVisibility(View.VISIBLE);
                Verify.setVisibility(View.INVISIBLE);
                PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(verifyId, code);
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, task -> {
                    progressBar2.setVisibility(View.GONE);
                    Verify.setVisibility(View.VISIBLE);
                    if(task.isSuccessful()){
                        Intent intent=new Intent(RegisterPage.this,SetAccountPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Toast.makeText(RegisterPage.this,"correct otp entered",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(RegisterPage.this, "The verification code entered is INVALID", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });


    }
}