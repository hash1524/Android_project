package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.Objects;

public class Dashboard extends AppCompatActivity {

    ImageButton todo,shop,tracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        todo=findViewById(R.id.todo);
        shop=findViewById(R.id.shop);
        tracker=findViewById(R.id.track);
        todo.setOnClickListener(v -> {
            Intent intent_todo=new Intent(Dashboard.this,TodoEvent.class);
            startActivity(intent_todo);
        });
        shop.setOnClickListener(v -> {
            Intent intent_shop=new Intent(Dashboard.this,Shop.class);
            startActivity(intent_shop);
        });
        tracker.setOnClickListener(v -> {
            Intent intent_track=new Intent(Dashboard.this,Tracker.class);
            startActivity(intent_track);
        });
    }
}