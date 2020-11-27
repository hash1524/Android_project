package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class toshopadder extends AppCompatActivity {
    EditText item_name_adder,item_quantity_adder;
    Button shop_add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toshopadder);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        shop_add_button=findViewById(R.id.shop_add_button);
        item_name_adder=findViewById(R.id.item_name_adder);
        item_quantity_adder=findViewById(R.id.item_quantity_adder);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        shop_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelperShop myDB_shop=new MyDatabaseHelperShop(toshopadder.this);
                myDB_shop.addItem(item_name_adder.getText().toString().trim(),item_quantity_adder.getText().toString().trim());
                Intent intent=new Intent(getApplicationContext(),Shop.class);
                startActivity(intent);
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent i2=new Intent(getApplicationContext(),Shop.class);
            startActivity(i2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}