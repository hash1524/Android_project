package com.example.myapplication;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class UpdateShop extends AppCompatActivity {
    EditText title_input,quantity_input;
    Button update_button,delete_button;

    String id,title,quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_shop);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        title_input=findViewById(R.id.item_name_adder_2);
        quantity_input=findViewById(R.id.item_quantity_adder_2);
        update_button=findViewById(R.id.shop_add_button_2);
        delete_button=findViewById(R.id.shop_delete_button);
        getAndSetIntentData();
        update_button.setOnClickListener(v -> {
            MyDatabaseHelperShop myDB=new MyDatabaseHelperShop(UpdateShop.this);
            title=title_input.getText().toString().trim();
            quantity=quantity_input.getText().toString().trim();
            myDB.updateData(id,title,quantity);
            Intent intent=new Intent(getApplicationContext(),Shop.class);
            startActivity(intent);
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("quantity")){
            id=getIntent().getStringExtra("id");
            title=getIntent().getStringExtra("title");
            quantity=getIntent().getStringExtra("quantity");
            title_input.setText(title);
            quantity_input.setText(quantity);
        }
        else{
            Toast.makeText(UpdateShop.this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent=new Intent(getApplicationContext(),Shop.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+title+" ?");
        builder.setMessage("Are you sure, you want to delete?"+title+" ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelperShop myDB=new MyDatabaseHelperShop(UpdateShop.this);
                myDB.deleteOneRow(id);
                Intent intent1=new Intent(getApplicationContext(),Shop.class);
                startActivity(intent1);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}