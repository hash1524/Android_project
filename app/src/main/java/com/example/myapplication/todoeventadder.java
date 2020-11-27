package com.example.myapplication;


import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Objects;

public class todoeventadder extends AppCompatActivity  {
    private static final String TAG = "todoeventadder";
    Button set_time;

    Button add_event;
    EditText settime, eventName;
    EditText setDate;
    CalendarView mcalender;
    TimePickerDialog timePickerDialog;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoeventadder);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        set_time = findViewById(R.id.set_time);
        eventName = findViewById(R.id.editTextTextPersonName);
        add_event = findViewById(R.id.add_event);
        settime = findViewById(R.id.editTextTime);
        settime.setInputType(InputType.TYPE_NULL);
        mcalender = findViewById(R.id.calendarView);
        setDate = findViewById(R.id.date);
        setDate.setInputType(InputType.TYPE_NULL);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mcalender.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String date = dayOfMonth + "/" + month + "/" + year;
            Log.d(TAG, "onSelectedDayChange: dd/mm/yyyy: " + date);
            setDate.setText(date);
        });
        set_time.setOnClickListener(v -> {
            final Calendar calender = Calendar.getInstance();
            int hours = calender.get(Calendar.HOUR_OF_DAY);
            int minute = calender.get(Calendar.MINUTE);
            timePickerDialog = new TimePickerDialog(todoeventadder.this,
                    (tp, sHour, sMinute) -> settime.setText(sHour + ":" + sMinute), hours, minute, true);
            timePickerDialog.show();
        });
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(todoeventadder.this);
                myDB.addEvent(eventName.getText().toString().trim(),setDate.getText().toString().trim(),settime.getText().toString().trim());
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent i2=new Intent(getApplicationContext(),TodoEvent.class);
            startActivity(i2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}