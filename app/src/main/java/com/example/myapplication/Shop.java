package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class Shop extends AppCompatActivity {
    FloatingActionButton add_button_shop;
    RecyclerView recyclerView_shop;
    MyDatabaseHelperShop myDB_shop;
    ArrayList<String> item_id,item_title,item_quantity;
    CustomAdapterShop customAdapter_shop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        recyclerView_shop=findViewById(R.id.recyclerView_shop);
        add_button_shop=findViewById(R.id.add_button_shop);
        add_button_shop.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),toshopadder.class);
            startActivity(intent);
        });
        myDB_shop=new MyDatabaseHelperShop(Shop.this);
        item_id=new ArrayList<>();
        item_title=new ArrayList<>();
        item_quantity=new ArrayList<>();
        storeDataInArrays();

        customAdapter_shop=new CustomAdapterShop(Shop.this,item_id,item_title,item_quantity);
        recyclerView_shop.setAdapter(customAdapter_shop);
        recyclerView_shop.setLayoutManager(new LinearLayoutManager(Shop.this));
    }
    void storeDataInArrays(){
        Cursor cursor=myDB_shop.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                item_id.add(cursor.getString(0));
                item_title.add(cursor.getString(1));
                item_quantity.add(cursor.getString(2));
            }
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent=new Intent(getApplicationContext(),Dashboard.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}