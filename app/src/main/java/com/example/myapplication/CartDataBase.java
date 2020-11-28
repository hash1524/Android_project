package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CartDataBase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="cart.db";
    private static final int DATABASE_VERSION=1;
    private static final String MyItems="MyItems";
    private static final String ProductId="ProductId";
    private static final String ProductName="ProductName";
    private static final String QuantityToBuy="QuantityToBuy";

    public CartDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE " + MyItems +
                " (" + ProductId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProductName + " TEXT, " +
                QuantityToBuy + " TEXT);";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+MyItems);
        onCreate(db);
    }
    void AddItem1(String productname, String  Quantitytobuy){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ProductName,productname);
        cv.put(QuantityToBuy,Quantitytobuy);
        long result=db.insert(MyItems,null,cv);
        if(result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query="SELECT * FROM "+MyItems;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
}