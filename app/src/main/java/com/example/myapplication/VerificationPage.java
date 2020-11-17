package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;

public class VerificationPage extends AppCompatActivity {

    PinView pinView;
    String verificationid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_page);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        Button verify=(Button) findViewById(R.id.Verify);
        ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressbar);
        verificationid=getIntent().getStringExtra("VerificationId");
        verify.setOnClickListener(v -> {
            if(pinView.getText().toString().trim().length() != 6){
                Toast.makeText(VerificationPage.this, "Enter a valid OTP", Toast.LENGTH_SHORT).show();
            }
            String code=pinView.getText().toString().trim();
            if(verificationid!=null){
                progressBar.setVisibility(View.VISIBLE);
                verify.setVisibility(View.INVISIBLE);
                PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(verificationid, code);
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    verify.setVisibility(View.VISIBLE);
                    if(task.isSuccessful()){
                        Intent intent=new Intent(getApplicationContext(),SetAccountPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(VerificationPage.this, "The verification code entered is INVALID", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}