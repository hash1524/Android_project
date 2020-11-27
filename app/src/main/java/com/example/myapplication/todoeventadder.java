package com.example.myapplication;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class todoeventadder extends AppCompatActivity  {
    private static final String TAG = "todoeventadder";
    Button set_time;

    Button add_event;
    EditText settime, eventName;
    EditText setDate;
    CalendarView mcalender;
    TimePickerDialog timePickerDialog;
    String monthstring,daystring,minstring,hourstring,yearstring;
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
            yearstring= "" + year;
            if((month+1)<10){
                monthstring="0"+(month+1);
            }
            else{
                monthstring=""+(month+1);
            }
            if((dayOfMonth)<10){
                daystring="0"+dayOfMonth;
            }
            else{
                daystring = "" + dayOfMonth;
            }
            String date = daystring + "/" + (monthstring) + "/" + year;
            Log.d(TAG, "onSelectedDayChange: dd/mm/yyyy: " + date);
            setDate.setText(date);
        });
        set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                int hours = calender.get(calender.HOUR_OF_DAY);
                int minute = calender.get(calender.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(todoeventadder.this, R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar  c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        if(hourOfDay < 10)
                        {
                            hourstring = "0"+hourOfDay;
                        }
                        else
                        {
                            hourstring = ""+hourOfDay;
                        }
                        if(minute < 10)
                        {
                            minstring = "0" + minute;
                        }
                        else {
                            minstring = "" + minute;
                        }
                        settime.setText(time);
                    }
                },hours,minute,false);
                timePickerDialog.show();
            }
        });
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(todoeventadder.this);
                myDB.addEvent(eventName.getText().toString().trim(),setDate.getText().toString().trim(),settime.getText().toString().trim());
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                String inputTime = yearstring + "-" + monthstring + "-" + daystring + " " + hourstring + ":" + minstring + ":" + 0;
                long diff = 0;
                try {
                    Date oldDate = dateFormat.parse(inputTime);
                    Date currentdate = new Date();
                    diff = oldDate.getTime() - currentdate.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                createNotificationChannel();

                Intent intent = new Intent(todoeventadder.this, RemainderBroadCast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(todoeventadder.this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                long timeAtButtonClick = System.currentTimeMillis();
                long tenSecondsInMillis = diff;
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        timeAtButtonClick + diff, pendingIntent);
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
    private void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "LemubitRemainderChannel";
            String description = "channel for lemubit remainder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifylemubit",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}