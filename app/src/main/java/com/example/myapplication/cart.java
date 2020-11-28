package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class cart extends AppCompatActivity {
    RecyclerView recyclerView;
    CartDataBase myDB;
    ArrayList<String> ProductId;
    ArrayList<String> ProductName;
    ArrayList<String> ProductQuantity;
    TrackAdapter TrackAdapter;
    FloatingActionButton AddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        AddItem = findViewById(R.id.AddItem);
        AddItem.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ItemAdder.class);
            startActivity(intent);
        });
        recyclerView = findViewById(R.id.recyclerViewCart);
        myDB = new CartDataBase(cart.this);
        ProductId = new ArrayList<>();
        ProductName = new ArrayList<>();
        ProductQuantity = new ArrayList<>();
        storeDataInArrays();
        TrackAdapter = new TrackAdapter(cart.this, ProductId, ProductName,  ProductQuantity);
        recyclerView.setAdapter(TrackAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(cart.this));
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                ProductId.add(cursor.getString(0));
                ProductName.add(cursor.getString(1));
                ProductQuantity.add(cursor.getString(2  ));
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}